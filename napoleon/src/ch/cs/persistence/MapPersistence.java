package ch.cs.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public final class MapPersistence
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final int ONE_KB = 1024;

    /** @TODO: javadoc! */
    private static final int TEN_KB = ONE_KB * 10;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new MapPersistence object.
     */
    private MapPersistence()
    {
        /**
         * Make constructor private in order to implement singleton correctly
         */
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     *
     * @param fileName javadoc!
     *
     * @return javadoc!
     */
    public static Map read(final String fileName)
    {
        Map map = new HashMap();
        XMLDecoder input = null;

        try
        {
            input =
                new XMLDecoder(new GZIPInputStream(
                        new BufferedInputStream(new FileInputStream(fileName),
                            TEN_KB)));
            map = (Map) input.readObject();
        }
        catch (Throwable e)
        {
            /**
             * Silently ignoring...
             */
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (Throwable e)
                {
                    /**
                     * Silently ignoring...
                     */
                }
            }
        }

        return map;
    }

    /**
     * @TODO: javadoc!
     *
     * @param fileName javadoc!
     * @param map javadoc!
     *
     * @return javadoc!
     */
    public static boolean write(final String fileName, final Map map)
    {
        try
        {
            XMLEncoder output =
                new XMLEncoder(new GZIPOutputStream(
                        new BufferedOutputStream(
                            new FileOutputStream(fileName), TEN_KB)));
            output.writeObject(map);
            output.close();

            return true;
        }
        catch (Throwable e)
        {
            return false;
        }
    }
}
