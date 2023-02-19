package job.usecase

import job.data.JobEntity

interface IAddJobUsecase {
    suspend fun addJob(jobData: JobEntity): Pair<Boolean, Error?>}