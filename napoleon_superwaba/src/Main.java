import ch.cs.napoleon.Napoleon;

import waba.sys.Vm;


/**
 * TODO: javadoc
 *
 * @author <a
 *         href="mailto:Christoph.Schilling@access.unizh.ch">Christoph
 *         Schilling</a>
 * @version $Revision$
 */
public class Main extends Napoleon
{
    //~ Methods ------------------------------------------------------

    /**
     * TODO: javadoc
     *
     * @param args TODO: javadoc
     */
    public static void main(String[] args)
    {
        try
        {
            new Main().setVisible(true);
        }
        catch (Throwable e)
        {
            Vm.debug(e.getMessage());
        }
    }
}
