package ch.cs.gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;


/**
 * TODO:
 *
 * @author <a href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public final class MessageProperties
{
    //~ Static fields/initializers ---------------------------------------------

    /** TODO: */
    private static final String BUNDLE_NAME = "ch.cs.gui.message"; //$NON-NLS-1$

    /** TODO: */
    private static final ResourceBundle RESOURCE_BUNDLE =
        ResourceBundle.getBundle(BUNDLE_NAME);

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new MessageProperties object.
     */
    private MessageProperties()
    {
        /**
         * Make constructor private in order to implement singleton correctly
         */
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * TODO:
     *
     * @param key TODO:
     *
     * @return TODO:
     */
    public static boolean getBoolean(final String key)
    {
        return Boolean.valueOf(getString(key)).booleanValue();
    }

    /**
     * TODO:
     *
     * @param key TODO:
     *
     * @return TODO:
     */
    public static boolean getBoolean(final Object[] key)
    {
        return getBoolean(createString(key));
    }

    /**
     * TODO:
     *
     * @param key1 TODO:
     * @param key2 TODO:
     *
     * @return TODO:
     */
    public static boolean getBoolean(final String key1, final String key2)
    {
        return getBoolean(createString(new Object[] { key1, key2 }));
    }

    /**
     * TODO:
     *
     * @param key1 TODO:
     * @param key2 TODO:
     * @param key3 TODO:
     *
     * @return TODO:
     */
    public static boolean getBoolean(final String key1, final String key2,
        final String key3)
    {
        return getBoolean(createString(new Object[] { key1, key2, key3 }));
    }

    /**
     * TODO:
     *
     * @param name TODO:
     *
     * @return TODO:
     */
    public static ImageIcon getImage(final String name)
    {
        return new ImageIcon(ClassLoader.getSystemResource(name));
    }

    /**
     * TODO:
     *
     * @param key TODO:
     *
     * @return TODO:
     */
    public static int getInt(final String key)
    {
        return Integer.valueOf(getString(key)).intValue();
    }

    /**
     * TODO:
     *
     * @param key TODO:
     *
     * @return TODO:
     */
    public static int getInt(final Object[] key)
    {
        return getInt(createString(key));
    }

    /**
     * TODO:
     *
     * @param key1 TODO:
     * @param key2 TODO:
     *
     * @return TODO:
     */
    public static int getInt(final String key1, final String key2)
    {
        return getInt(createString(new Object[] { key1, key2 }));
    }

    /**
     * TODO:
     *
     * @param key1 TODO:
     * @param key2 TODO:
     * @param key3 TODO:
     *
     * @return TODO:
     */
    public static int getInt(final String key1, final String key2,
        final String key3)
    {
        return getInt(createString(new Object[] { key1, key2, key3 }));
    }

    /**
     * TODO:
     *
     * @param key TODO:
     *
     * @return TODO:
     */
    public static String getString(final Object[] key)
    {
        return getString(createString(key));
    }

    /**
     * TODO:
     *
     * @param key1 TODO:
     * @param key2 TODO:
     *
     * @return TODO:
     */
    public static String getString(final String key1, final String key2)
    {
        return getString(createString(new Object[] { key1, key2 }));
    }

    /**
     * TODO:
     *
     * @param key1 TODO:
     * @param key2 TODO:
     * @param key3 TODO:
     *
     * @return TODO:
     */
    public static String getString(final String key1, final String key2,
        final String key3)
    {
        return getString(createString(new Object[] { key1, key2, key3 }));
    }

    /**
     * TODO:
     *
     * @param key TODO:
     *
     * @return TODO:
     */
    public static String getString(final String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }

    /**
     * TODO:
     *
     * @param property TODO:
     *
     * @return TODO:
     */
    private static String createString(final Object[] property)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(property[0]);

        for (int i = 1; i < property.length; i++)
        {
            buffer.append("."); //$NON-NLS-1$
            buffer.append(property[i]);
        }

        return buffer.toString();
    }
}
