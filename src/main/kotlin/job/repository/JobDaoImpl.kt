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
        JobEntities
            .select { JobEntities.userId eq userId  }
            .map(::resultRowToJobsEntity)    }

    override suspend fun addJob(jobData: JobEntity): JobEntity? = dbQuery {
        JobEntities.insert {
            it[JobEntities.job_id] = jobData.job_id
            it[JobEntities.name] = jobData.name
            it[JobEntities.job_description] = jobData.job_description
            it[delivery_address] = jobData.delivery_address
            it[payment] = jobData.payment
            it[contact] = jobData.contact
            it[userId] = jobData.userId
        }
        jobData
    }

    override suspend fun getAllJobs(): List<JobEntity?> =
        dbQuery {
            JobEntities.selectAll().map(::resultRowToJobsEntity)
        }






}

