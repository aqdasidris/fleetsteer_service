package job.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.JobDao
import job.data.JobEntities
import job.data.JobEntity
import kotlinx.coroutines.runBlocking
import login.data.UserDataTable
import org.h2.engine.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.lang.IllegalArgumentException
import java.util.logging.Logger

class JobDaoImpl : JobDao {
    private fun resultRowToJobsEntity(row: ResultRow) = JobEntity(
        job_id = row[JobEntities.job_id],
        name = row[JobEntities.name],
        job_description = row[JobEntities.job_description],
        pickup_address = row[JobEntities.pickup_address],
        delivery_address = row[JobEntities.delivery_address],
        contact = row[JobEntities.contact],
        payment = row[JobEntities.payment],
        userId = row[JobEntities.userId]
    )


    override suspend fun getJob(userId: Long): List<JobEntity?> = dbQuery {
        val jobs = JobEntities
            .select { JobEntities.userId eq userId }
            .map {
                JobEntity(
                    job_id = it[JobEntities.job_id],
                    name = it[JobEntities.name],
                    job_description = it[JobEntities.job_description],
                    pickup_address = it[JobEntities.pickup_address],
                    delivery_address = it[JobEntities.delivery_address],
                    payment = it[JobEntities.payment],
                    contact = it[JobEntities.contact],
                    userId = it[JobEntities.userId]
                )
            }
        return@dbQuery jobs
    }

    override suspend fun addJob(jobData: JobEntity): Int {
        var result=-1
        dbQuery {
            try {
                result=insertJob(jobData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return result
    }


    private suspend fun insertJob(jobEntity: JobEntity): Int {
        val result = dbQuery {
            JobEntities.insert {
                // it[JobEntities.job_id] = jobEntity.job_id
                it[JobEntities.name] = jobEntity.name
                it[JobEntities.job_description] = jobEntity.job_description
                it[pickup_address] = jobEntity.pickup_address
                it[delivery_address] = jobEntity.delivery_address
                it[payment] = jobEntity.payment
                it[contact] = jobEntity.contact
                it[userId] = jobEntity.userId
            }
        }
        println("ADDED RESPONSE${result.resultedValues?.get(0)}")
        val id=result.resultedValues?.get(0)?.get(JobEntities.job_id)
        return id ?: -1
    }

    override suspend fun getAllJobs(): List<JobEntity?> =
        dbQuery {
            JobEntities.selectAll().map(::resultRowToJobsEntity)
        }

    override suspend fun getJobById(jobId: Int): List<JobEntity?> {
        try {
            return selectJobsById(jobId)
        } catch (e: IllegalArgumentException) {
            println("Job id not found $e")
        }
        return emptyList()
    }

    override suspend fun delete(job: JobEntity) {
        dbQuery { JobEntities.deleteWhere { JobEntities.job_id eq job.job_id } }
    }

    private suspend fun selectJobsById(jobId: Int): List<JobEntity?> = dbQuery {
        val jobsByID = JobEntities
            .select { JobEntities.job_id eq jobId }
            .map {
                JobEntity(
                    job_id = it[JobEntities.job_id],
                    name = it[JobEntities.name],
                    job_description = it[JobEntities.job_description],
                    pickup_address = it[JobEntities.pickup_address],
                    delivery_address = it[JobEntities.delivery_address],
                    payment = it[JobEntities.payment],
                    contact = it[JobEntities.contact],
                    userId = it[JobEntities.userId]
                )
            }
        return@dbQuery jobsByID
    }


}

