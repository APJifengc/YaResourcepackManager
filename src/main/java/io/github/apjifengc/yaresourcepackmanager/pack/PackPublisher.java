package io.github.apjifengc.yaresourcepackmanager.pack;

import fi.iki.elonen.NanoHTTPD;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

public class PackPublisher extends NanoHTTPD {
    private final Logger logger = YaResourcepackManager.getInstance().getLogger();
    private final File file;

    public PackPublisher(int port, File file) {
        super(port);
        this.file = file;
    }

    public void startService() throws IOException {
        logger.info("Resourcepack service starting...");
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        logger.info("Resourcepack service started.");
    }
    public void stopService() {
        logger.info("Resourcepack service stopping...");
        stop();
        logger.info("Resourcepack service stopped.");
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            FileInputStream file = new FileInputStream(this.file);
            return newFixedLengthResponse(Response.Status.OK, mimeTypes().get("zip"), file, file.available());
        } catch (IOException e) {
            e.printStackTrace();
            return newFixedLengthResponse(Response.Status.NOT_FOUND, mimeTypes().get("txt"), "404");
        }
    }
}
