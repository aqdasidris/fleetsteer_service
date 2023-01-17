package job.repository

import job.data.JobData

interface IJobRepository {
    fun getData(job_id:Int): JobData?
}