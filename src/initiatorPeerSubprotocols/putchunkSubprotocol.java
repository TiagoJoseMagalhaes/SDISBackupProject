package initiatorPeerSubprotocols;

import FileHandler.FileIDCalculator;
import FileHandler.FileStreamer;
import Utils.threadRegistry;

import java.io.IOException;

public class putchunkSubprotocol {

    private final int CHUNK_BODY_SIZE = 64000;

    private String          startChunk;
    private String          filepath;
    private int             senderID;
    private String          fileID;
    private threadRegistry  registry;

    /**
     * 
     * @param senderID
     * @param filepath
     * @param registry
     * @throws IOException
     */
    public putchunkSubprotocol(int senderID, String filepath, threadRegistry registry) throws IOException {
        this.filepath = filepath;
        this.senderID = senderID;

        FileStreamer stream = new FileStreamer(this.CHUNK_BODY_SIZE,filepath);
        this.startChunk = stream.read();

        FileIDCalculator cal = new FileIDCalculator(this.startChunk,filepath,senderID);
        this.fileID = cal.calculateFileID();

        //create stub object
        //register stub object
        //encapsulate in thread
        //run it

        //repeat until file has been read
    }

}
