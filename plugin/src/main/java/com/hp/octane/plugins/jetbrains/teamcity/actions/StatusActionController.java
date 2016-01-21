package com.hp.octane.plugins.jetbrains.teamcity.actions;

import com.hp.nga.integrations.dto.general.PluginInfo;
import com.hp.octane.plugins.jetbrains.teamcity.NGAPlugin;
import com.hp.octane.plugins.jetbrains.teamcity.utils.Config;
import jetbrains.buildServer.responsibility.BuildTypeResponsibilityFacade;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lazara on 27/12/2015.
 */
public class StatusActionController extends AbstractActionController {
//    private final SBuildServer myServer;
//    private final ProjectManager projectManager;
//    private final BuildTypeResponsibilityFacade responsibilityFacade;

    public StatusActionController(SBuildServer server, ProjectManager projectManager, BuildTypeResponsibilityFacade responsibilityFacade) {
            super(server,projectManager,responsibilityFacade,null);
//        this.myServer = server;
//        this.projectManager = projectManager;
//        this.responsibilityFacade = responsibilityFacade;
    }

    @Override
    protected Object buildResults(HttpServletRequest request, HttpServletResponse response) {
        return new PluginStatus();
    }

    //TODO:Add to common lib
    public static final class ServerInfo {
        private static final String type = "teamcity";
        private static final String version = "9.1.5";
        private String url;

        NGAPlugin ngaPlugin = NGAPlugin.getInstance();
        Config cfg = ngaPlugin.getConfig();

        private String instanceId = cfg.getIdentity();//Jenkins.getInstance().getPlugin(OctanePlugin.class).getIdentity();
        private Long instanceIdFrom = cfg.getIdentityFromAsLong();//Jenkins.getInstance().getPlugin(OctanePlugin.class).getIdentityFrom();
        private Long sendingTime;

        public ServerInfo() {
            String serverUrl = "http://locahost:8081";
            if (serverUrl != null && serverUrl.endsWith("/"))
                serverUrl = serverUrl.substring(0, serverUrl.length() - 1);
            this.url = serverUrl;
            this.sendingTime = System.currentTimeMillis();
        }


        public String getType() {
            return type;
        }

        public String getVersion() {
            return version;
        }

        public String getUrl() {
            return url;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public Long getInstanceIdFrom() {
            return instanceIdFrom;
        }

        public Long getSendingTime() {
            return sendingTime;
        }
    }
    public static final class PluginStatus {
        public ServerInfo getServer() {
            return new ServerInfo();
        }

        public PluginInfo getPlugin() {
            return new PluginInfo();
        }

//        public List<EventsClient> getEventsClients() {
//            return EventsService.getExtensionInstance().getStatus();
//        }
    }
}