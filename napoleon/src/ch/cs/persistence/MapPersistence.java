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
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
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
     * Creates a new MapPersistence object.
     *
     * @param fileName TODO:
     *
     * @return TODO:
     */

    /* public static Map read(String fileName)
       {
           Map map;
           ObjectInputStream input = null;
           try
           {
               input =
                   new ObjectInputStream(
                       new GZIPInputStream(
                           new BufferedInputStream(
                               new FileInputStream(fileName),
                               10240)));
               map = (Map) input.readObject();
           }
           catch (Throwable e)
           {
               map = new HashMap();
               // Silently ignoring...
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
                       // Silently ignoring...
                   }
               }
           }
           return map;
       } */

    /**
     * TODO:
     *
     * @param fileName TODO:
     *
     * @return TODO:
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
     * TODO:
     *
     * @param fileName TODO:
     * @param map TODO:
     *
     * @return TODO:
     */

    /* public static Object toObject(String byteArray)
       {
           ByteArrayInputStream input =
               new ByteArrayInputStream(byteArray.getBytes());
           Object object = null;
           try
           {
               ObjectInputStream objectInput =
                   new ObjectInputStream(input);
               object = objectInput.readObject();
           }
           catch (Throwable e)
           {
               // Silently ignoring...
           }
           return object;
       } */

    /**
     * TODO:
     *
     * @param fileName TODO:
     * @param map TODO:
     *
     * @return TODO:
     */

    /* public static String toString(Object object)
       {
           ByteArrayOutputStream output = new ByteArrayOutputStream();
           try
           {
               ObjectOutputStream objectOutput =
                   new ObjectOutputStream(output);
               objectOutput.writeObject(object);
           }
           catch (Throwable e)
           {
               // Silently ignoring...
           }
           return new String(output.toByteArray());
       } */

    /**
     * TODO:
     *
     * @param fileName TODO:
     * @param map TODO:
     *
     * @return TODO:
     */

    /* public static boolean write(String fileName, Map map)
       {
           try
           {
               ObjectOutputStream output =
                   new ObjectOutputStream(new GZIPOutputStream(
                           new BufferedOutputStream(
                               new FileOutputStream(fileName),
                               10240)));
               output.writeObject(map);
               output.close();
               return true;
           }
           catch (Throwable e)
           {
               return false;
           }
       } */

    /**
     * TODO:
     *
     * @param fileName TODO:
     * @param map TODO:
     *
     * @return TODO:
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
