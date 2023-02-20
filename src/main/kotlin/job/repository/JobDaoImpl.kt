package job.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.JobDao
import job.data.JobEntities
import job.data.JobEntity
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.logging.Logger

class JobDaoImpl : JobDao {
    private fun resultRowToJobsEntity(row: ResultRow) = JobEntity(
        job_id = row[JobEntities.job_id],
        name = row[JobEntities.name],
        job_description = row[JobEntities.job_description],
        delivery_address = row[JobEntities.delivery_address],
        contact = row[JobEntities.contact],
        payment = row[JobEntities.payment],
        userId = row[JobEntities.userId]
    )


    override suspend fun getJob(userId: Long): List<JobEntity?> = dbQuery{
        val jobs=JobEntities
            .select { JobEntities.userId eq userId  }
            .map{
                JobEntity(
                    job_id = it[JobEntities.job_id],
                    name = it[JobEntities.name],
                    job_description = it[JobEntities.job_description],
                    delivery_address = it[JobEntities.delivery_address],
                    payment = it[JobEntities.payment],
                    contact = it[JobEntities.contact],
                    userId=it[JobEntities.userId]
                )
            }
            return@dbQuery jobs
    }

    override suspend fun addJob(jobData: JobEntity): Boolean = dbQuery {
        try {
            insertJob(jobData)
        }catch (e:Exception){
            e.printStackTrace()
        }
        true
    }


    private suspend fun insertJob(jobEntity: JobEntity)= dbQuery {
        JobEntities.insert {
           // it[JobEntities.job_id] = jobEntity.job_id
            it[JobEntities.name] = jobEntity.name
            it[JobEntities.job_description] = jobEntity.job_description
            it[delivery_address] = jobEntity.delivery_address
            it[payment] = jobEntity.payment
            it[contact] = jobEntity.contact
            it[userId] = jobEntity.userId
        }
    }
    override suspend fun getAllJobs(): List<JobEntity?> =
        dbQuery {
            JobEntities.selectAll().map(::resultRowToJobsEntity)
        }






}

