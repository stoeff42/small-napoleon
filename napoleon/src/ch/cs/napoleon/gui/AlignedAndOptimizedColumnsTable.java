package ch.cs.napoleon.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 * @TODO: javadoc!
 *
 * @author $author$
 * @version $Revision$
 */
public class AlignedAndOptimizedColumnsTable extends JTable
{
    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new AlignedAndOptimizedColumnsTable object.
     *
     * @param data javadoc!
     * @param columnNames javadoc!
     * @param alignments javadoc!
     */
    public AlignedAndOptimizedColumnsTable(final Object[][] data,
        final Object[] columnNames, final int[] alignments)
    {
        super(data, columnNames);
        this.setDefaultRenderer(Object.class,
            this.makeAligningRenderer(this.getDefaultRenderer(Object.class),
                alignments));
        this.getTableHeader().setDefaultRenderer(this.makeAligningRenderer(
                this.getTableHeader().getDefaultRenderer(), alignments));
        this.optimizeColumnWidths();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @TODO: javadoc!
     */
    public final void optimizeColumnWidths()
    {
        for (int i = 0; i < this.getColumnCount(); i++)
        {
            int headerWidth =
                this.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(null,
                    this.getColumnModel().getColumn(i).getHeaderValue(), false,
                    false, 0, i).getPreferredSize().width;

            for (int j = 0; j < this.getRowCount(); j++)
            {
                headerWidth =
                    Math.max(headerWidth,
                        this.getDefaultRenderer(this.getModel().getColumnClass(i))
                            .getTableCellRendererComponent(this,
                            this.getValueAt(j, i), false, false, j, i)
                            .getPreferredSize().width);
            }

            this.getColumnModel().getColumn(i).setPreferredWidth(headerWidth
                + (2 * this.getIntercellSpacing().width) + 2);
        }
    }

    /**
     * @TODO: javadoc!
     *
     * @param renderer javadoc!
     * @param alignments javadoc!
     *
     * @return javadoc!
     */
    private TableCellRenderer makeAligningRenderer(
        final TableCellRenderer renderer, final int[] alignments)
    {
        return new TableCellRenderer()
            {
                public final Component getTableCellRendererComponent(
                    final JTable table, final Object value,
                    final boolean isSelected, final boolean hasFocus,
                    final int row, final int column)
                {
                    JLabel component =
                        (JLabel) renderer.getTableCellRendererComponent(table,
                            value, isSelected, hasFocus, row, column);
                    component.setHorizontalTextPosition(alignments[column]);
                    component.setHorizontalAlignment(alignments[column]);

                    return component;
                }
            };
    }
}
