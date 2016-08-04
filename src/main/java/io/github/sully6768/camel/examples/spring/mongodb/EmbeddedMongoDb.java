/**
 * 
 */
package io.github.sully6768.camel.examples.spring.mongodb;

import static de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION;
import static de.flapdoodle.embed.process.runtime.Network.localhostIsIPv6;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;

/**
 * @author scottes
 *
 */
@Configuration
public class EmbeddedMongoDb {

    private static final int PORT = 27017;
    
    private MongodExecutable mongodExecutable;
    
    public void init() {

        try {
            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(PRODUCTION)
                    .net(new Net(PORT, localhostIsIPv6()))
                    .build();
            mongodExecutable = MongodStarter.getDefaultInstance().prepare(mongodConfig);
            mongodExecutable.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
    
    public void destroy() {
    	if (mongodExecutable != null)
    		mongodExecutable.stop();
	}

}
