package MessageHandler;

import FileHandler.FileIDCalculator;

import java.io.IOException;

public abstract class Message {

    protected String                    protocolVersion = "1.0";    // The protocol version 1.0 by default
    protected int                       senderID;                   // The Sender ID , Message Specific
    protected String                    FileID;                     // The File ID , File specific
    protected int                       chunkNO;                    // The Chunk Number of the message, message and file specific
    protected messageType               messageType;                // The Message Type, Message Specific

    /**
     * Converts the Message class to its string representation
     * @return Message String Representation
     */
    @Override
    public abstract String toString();

    /**
     * Obtain the message protocol version
     * @return The protocol version
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Modifies the message protocol version
     * @param protocolVersion value to set the protocol version to
     */
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * obtain the sender Id of the message
     * @return the sender id
     */
    public int getSenderID() {
        return senderID;
    }

    /**
     * set the sender id of the message
     * @param senderID value to give the sender id
     */
    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    /**
     * obtain the file id of the message
     * @return the file id
     */
    public String getFileID() {
        return FileID;
    }

    /**
     * set the file id of the message
     * @param fileID value to give the file id
     */
    public void setFileID(String fileID) {
        FileID = fileID;
    }

    /**
     * obtain the chunk number of the message if it has one
     * @return the chunk number
     */
    public int getChunkNO() {
        return chunkNO;
    }

    /**
     * set the value of the message chunk number
     * @param chunkNO value to give the chunk number
     */
    public void setChunkNO(int chunkNO) {
        this.chunkNO = chunkNO;
    }

    /**
     * obtain the message type of the message
     * @return the message type
     */
    public messageType getMessageType() {
        return messageType;
    }

    /**
     * set the message type of the message
     * @param messageType value to give the message type
     */
    public void setMessageType(messageType messageType) {
        this.messageType = messageType;
    }

    public static messageType getType(String messageType){
        messageType ret;
        switch (messageType){
            case("PUTCHUNK"):{
                ret= MessageHandler.messageType.PUTCHUNK;
                break;
            }
            case("GETCHUNK"):{
                ret= MessageHandler.messageType.GETCHUNK;
                break;
            }
            case("STORED"):{
                ret= MessageHandler.messageType.STORED;
                break;
            }
            case("DELETE"):{
                ret= MessageHandler.messageType.DELETE;
                break;
            }
            case("REMOVED"):{
                ret= MessageHandler.messageType.REMOVED;
                break;
            }
            case("CHUNK"):{
                ret= MessageHandler.messageType.CHUNK;
                break;
            }
            default:{
                ret=null;
                break;
            }
        }
        return ret;
    }

    /**
     * Parse the message and create the appropriate message object
     * @param message the message to be parsed
     * @return message object representing the message
     */
    public static Message ParseMessage(String message){
       String[] components =  message.split("\\n\\r\\s+\\n\\r");
       int len = components.length;

        String[] headerArgs = components[0].split("\\s+");
        String body = null;
       if(len==2){
           body = components[1].trim();
       }

        MessageHandler.messageType type = Message.getType(headerArgs[0]);
       switch (type){
           case PUTCHUNK: {
               break;
           }
           case STORED: {
               break;
           }
           case CHUNK: {
               break;
           }
           case DELETE: {
               break;
           }
           case REMOVED: {
               break;
           }
           case GETCHUNK: {
               break;
           }
           default:{
                return null;
           }
       }
       return null;
    }

    /**
     * calculates the file id
     * @param startChunk the start chunk of data from the file we are transmiting
     * @param Filename the filename of the file we are sending
     * @return string containing the file if
     * @throws IOException in case the specified file is not found
     */
    protected String calculateFileID(String startChunk,String Filename) throws IOException {
        FileIDCalculator cal = new FileIDCalculator(startChunk,Filename,this.senderID);
        return cal.calculateFileID();
    }

}
