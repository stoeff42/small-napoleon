package ch.cs.napoleon.score;

import waba.io.DataStream;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class ScoreEntry {
    /** TODO: javadoc */
    private String name;

    /** TODO: javadoc */
    private int nrOfMoves;

    /** TODO: javadoc */
    private int time;

    /**
     * Creates a new ScoreEntry object.
     */
    public ScoreEntry() {
    }

    /**
     * Creates a new ScoreEntry object.
     *
     * @param name TODO: javadoc
     * @param nrOfMoves TODO: javadoc
     * @param timeUsed TODO: javadoc
     */
    public ScoreEntry(String nme, int nrOMvs, int timeUsed) {
        this.name = nme;
        this.nrOfMoves = nrOMvs;
        this.time = timeUsed;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public static Object[] getHeaderNames() {
        return new Object[] { "Name", "Moves", "Time" };
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public String getName() {
        return this.name;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getNrOfMoves() {
        return this.nrOfMoves;
    }

    /**
     * TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int getTime() {
        return this.time;
    }

    /**
     * TODO: javadoc
     *
     * @param obj TODO: javadoc
     *
     * @return TODO: javadoc
     */
    public int compareTo(Object obj) {
        if (!(obj instanceof ScoreEntry)) {
            return 1;
        }

        ScoreEntry other = (ScoreEntry) obj;

        if (this.getNrOfMoves() < other.getNrOfMoves()) {
            return -1;
        }

        if (this.getNrOfMoves() > other.getNrOfMoves()) {
            return 1;
        }

        if (this.getTime() < other.getTime()) {
            return -1;
        }

        if (this.getTime() > other.getTime()) {
            return 1;
        }

        return this.getName().compareTo(other.getName());
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void load(DataStream stream) {
        this.name = stream.readCString();
        this.nrOfMoves = stream.readInt();
        this.time = stream.readInt();
    }

    /**
     * TODO: javadoc
     *
     * @param stream TODO: javadoc
     */
    public void save(DataStream stream) {
        stream.writeCString(this.name);
        stream.writeInt(this.nrOfMoves);
        stream.writeInt(this.time);
    }
}
