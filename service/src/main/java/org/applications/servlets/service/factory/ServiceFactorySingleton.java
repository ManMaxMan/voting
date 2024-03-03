package org.applications.servlets.service.factory;

import org.applications.servlets.dao.factory.DaoFactory;
import org.applications.servlets.service.StatService;
import org.applications.servlets.service.VoteService;
import org.applications.servlets.service.api.interfaces.IStatService;
import org.applications.servlets.service.api.interfaces.IVoteService;

public class ServiceFactorySingleton {
    private volatile static IStatService statService;
    private volatile static IVoteService voteService;

    public static IStatService getStatService(){
        if(statService == null){
            synchronized (ServiceFactorySingleton.class){
                if(statService == null){
                    statService = new StatService(getVoteService());
                }
            }
        }
        return statService;
    }

    public static IVoteService getVoteService(){
        if(voteService == null){
            synchronized (ServiceFactorySingleton.class){
                if(voteService == null){
                    voteService = new VoteService(DaoFactory.getVoteDao());
                }
            }
        }
        return voteService;
    }
}
