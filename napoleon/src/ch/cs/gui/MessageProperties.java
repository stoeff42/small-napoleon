package ch.cs.gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public final class MessageProperties
{
    //~ Static fields/initializers ---------------------------------------------

    /** @TODO: javadoc! */
    private static final String BUNDLE_NAME = "ch.cs.gui.message"; //$NON-NLS-1$

    /** @TODO: javadoc! */
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
     * @TODO: javadoc!
     *
     * @param key javadoc!
     *
     * @return javadoc!
     */
    public static boolean getBoolean(final String key)
    {
        return Boolean.valueOf(getString(key)).booleanValue();
    }

    /**
     * @TODO: javadoc!
     *
     * @param key javadoc!
     *
     * @return javadoc!
     */
    public static boolean getBoolean(final Object[] key)
    {
        return getBoolean(createString(key));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key1 javadoc!
     * @param key2 javadoc!
     *
     * @return javadoc!
     */
    public static boolean getBoolean(final String key1, final String key2)
    {
        return getBoolean(createString(new Object[] { key1, key2 }));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key1 javadoc!
     * @param key2 javadoc!
     * @param key3 javadoc!
     *
     * @return javadoc!
     */
    public static boolean getBoolean(final String key1, final String key2,
        final String key3)
    {
        return getBoolean(createString(new Object[] { key1, key2, key3 }));
    }

    /**
     * @TODO: javadoc!
     *
     * @param name javadoc!
     *
     * @return javadoc!
     */
    public static ImageIcon getImage(final String name)
    {
        return new ImageIcon(ClassLoader.getSystemResource(name));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key javadoc!
     *
     * @return javadoc!
     */
    public static int getInt(final String key)
    {
        return Integer.valueOf(getString(key)).intValue();
    }

    /**
     * @TODO: javadoc!
     *
     * @param key javadoc!
     *
     * @return javadoc!
     */
    public static int getInt(final Object[] key)
    {
        return getInt(createString(key));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key1 javadoc!
     * @param key2 javadoc!
     *
     * @return javadoc!
     */
    public static int getInt(final String key1, final String key2)
    {
        return getInt(createString(new Object[] { key1, key2 }));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key1 javadoc!
     * @param key2 javadoc!
     * @param key3 javadoc!
     *
     * @return javadoc!
     */
    public static int getInt(final String key1, final String key2,
        final String key3)
    {
        return getInt(createString(new Object[] { key1, key2, key3 }));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key javadoc!
     *
     * @return javadoc!
     */
    public static String getString(final Object[] key)
    {
        return getString(createString(key));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key1 javadoc!
     * @param key2 javadoc!
     *
     * @return javadoc!
     */
    public static String getString(final String key1, final String key2)
    {
        return getString(createString(new Object[] { key1, key2 }));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key1 javadoc!
     * @param key2 javadoc!
     * @param key3 javadoc!
     *
     * @return javadoc!
     */
    public static String getString(final String key1, final String key2,
        final String key3)
    {
        return getString(createString(new Object[] { key1, key2, key3 }));
    }

    /**
     * @TODO: javadoc!
     *
     * @param key javadoc!
     *
     * @return javadoc!
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
     * @TODO: javadoc!
     *
     * @param property javadoc!
     *
     * @return javadoc!
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
