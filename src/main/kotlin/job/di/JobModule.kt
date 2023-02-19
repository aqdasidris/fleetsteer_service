package job.di

import common.dao.JobDao
import dagger.Module
import dagger.Provides
import job.repository.IJobRepository
import job.repository.JobDaoImpl
import job.repository.JobRepository
import javax.inject.Singleton


@Module
@Singleton
class JobModule {

    @Provides
    fun providesJobDao():JobDao{
        return JobDaoImpl()
    }
    @Provides
    fun providesJobRepository(jobDao: JobDao):IJobRepository{
        return JobRepository(jobDao)
    }


}