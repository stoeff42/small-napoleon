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
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class MapPersistence
{
    //~ Methods ------------------------------------------------------

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
    public static Map read(String fileName)
    {
        Map map = new HashMap();
        XMLDecoder input = null;
        try
        {
            input =
                new XMLDecoder(
                    new GZIPInputStream(
                        new BufferedInputStream(
                            new FileInputStream(fileName),
                            10240)));
            map = (Map) input.readObject();
        }
        catch (Throwable e)
        {
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
    public static boolean write(String fileName, Map map)
    {
        try
        {
            XMLEncoder output =
                new XMLEncoder(new GZIPOutputStream(
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
    }
}
