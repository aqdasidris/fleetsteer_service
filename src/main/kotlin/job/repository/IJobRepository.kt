package job.repository

import job.data.JobEntity

interface IJobRepository {
    fun getData(job_id:Int): JobEntity?
}