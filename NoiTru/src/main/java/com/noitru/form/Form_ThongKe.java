package com.noitru.form;

import com.noitru.BenhNhan;
import com.noitru.model.ModelChart;
import com.noitru.model.ModelLegend;
import com.noitru.swing.LegendItem;
import com.noitru.swing.ModelPieChart;
import com.noitru.swing.PieChart;

import com.noitru.swing.blankchart.BlankPlotChart;
import com.noitru.swing.blankchart.BlankPlotChatRender;
import com.noitru.swing.blankchart.SeriesSize;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public final class Form_ThongKe extends javax.swing.JPanel {

    private List<ModelLegend> legends = new ArrayList<>();
    private static List<ModelLegend> legendsPie = new ArrayList<>();
    private List<ModelChart> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriesSpace = 6;
    private Animator animator;
    private float animate;
    private static final Color[] CHART_COLORS = {
        new Color(23, 126, 238), // Xanh dương
        new Color(85, 173, 65), // Xanh lá
        new Color(235, 64, 52), // Đỏ
        new Color(255, 182, 29), // Vàng cam
        new Color(146, 39, 245), // Tím
        new Color(243, 89, 169), // Hồng
        new Color(33, 184, 184), // Xanh ngọc
        new Color(255, 127, 39), // Cam
        new Color(168, 168, 168), // Xám
        new Color(133, 89, 165) // Tím nhạt
    };

    public Form_ThongKe() {
        initComponents();
        pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
        addLegend("Chờ Xét Nghiệm", new Color(245, 189, 135));
        addLegend("Đã Khám", new Color(135, 189, 245));
        addLegend("Đã Nhập Viện", new Color(189, 135, 245));
        addLegend("Đã Xuất Viện", new Color(139, 229, 222));
        addData(new ModelChart("Tháng 1", BenhNhan.demSoBenhNhanTheoTinhTrang(1)));
        addData(new ModelChart("Tháng 2", BenhNhan.demSoBenhNhanTheoTinhTrang(2)));
        addData(new ModelChart("Tháng 3", BenhNhan.demSoBenhNhanTheoTinhTrang(3)));
        addData(new ModelChart("Tháng 4", BenhNhan.demSoBenhNhanTheoTinhTrang(4)));
        addData(new ModelChart("Tháng 5", BenhNhan.demSoBenhNhanTheoTinhTrang(5)));
        addData(new ModelChart("Tháng 6", BenhNhan.demSoBenhNhanTheoTinhTrang(6)));
        addData(new ModelChart("Tháng 7", BenhNhan.demSoBenhNhanTheoTinhTrang(7)));
        addData(new ModelChart("Tháng 8", BenhNhan.demSoBenhNhanTheoTinhTrang(8)));
        addData(new ModelChart("Tháng 9", BenhNhan.demSoBenhNhanTheoTinhTrang(9)));
        addData(new ModelChart("Tháng 10", BenhNhan.demSoBenhNhanTheoTinhTrang(10)));
        addData(new ModelChart("Tháng 11", BenhNhan.demSoBenhNhanTheoTinhTrang(11)));
        addData(new ModelChart("Tháng 12", BenhNhan.demSoBenhNhanTheoTinhTrang(12)));
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        blankPlotChart.setBlankPlotChatRender(new BlankPlotChatRender() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {
                double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
                double x = (size.getWidth() - totalSeriesWidth) / 2;
                for (int i = 0; i < legends.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    g2.setColor(legend.getColor());
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight()) * animate;
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues), seriesSize, (int) seriesValues);
                    x += seriesSpace + seriesSize;
                }
            }
        });
        start();
        Map<String, Integer> statistics = BenhNhan.getBenhNhanTheoDiaChi();
        addDataToPieChart(pieChart1, statistics);
        addDataToLegendPie(panelLegendPie, statistics);
    }

    public static void addDataToPieChart(PieChart pieChart, Map<String, Integer> data) {
        int colorIndex = 0;

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            // Lấy màu từ mảng màu, quay vòng nếu hết màu
            Color color = CHART_COLORS[colorIndex % CHART_COLORS.length];

            // Thêm dữ liệu vào biểu đồ với màu tương ứng
            pieChart.addData(new ModelPieChart("Bệnh nhân ở "
                    + entry.getKey(), // Tên (địa chỉ)
                    entry.getValue(), // Giá trị (số lượng)
                    color // Màu sắc
            ));

            colorIndex++;
        }
    }

    public static void addDataToLegendPie(JPanel a, Map<String, Integer> data) {
        int colorIndex = 0;

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            // Lấy màu từ mảng màu, quay vòng nếu hết màu
            Color color = CHART_COLORS[colorIndex % CHART_COLORS.length];

            // Thêm dữ liệu vào biểu đồ với màu tương ứng
            addLegendPie(a, entry.getKey(), color);

            colorIndex++;
        }
    }

    public static void addLegendPie(JPanel panelLegendPie, String name, Color color) {
        ModelLegend data = new ModelLegend(name, color);
        // Giả sử legendsPie là một List static hoặc thuộc về class
        legendsPie.add(data);
        panelLegendPie.add(new LegendItem(data));
        panelLegendPie.repaint();
        panelLegendPie.revalidate();
    }

    public void addLegend(String name, Color color) {
        ModelLegend data = new ModelLegend(name, color);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }

    public void addData(ModelChart data) {
        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) {
            blankPlotChart.setMaxValues(max);
        }
    }

    public void clear() {
        animate = 0;
        blankPlotChart.setLabelCount(0);
        model.clear();
        repaint();
    }

    public void start() {
        if (!animator.isRunning()) {
            animator.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.noitru.swing.PanelBorder();
        blankPlotChart = new com.noitru.swing.blankchart.BlankPlotChart();
        panelLegend = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelBorder2 = new com.noitru.swing.PanelBorder();
        pieChart1 = new com.noitru.swing.PieChart();
        panelLegendPie = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(227, 227, 255));

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));
        panelBorder1.setForeground(new java.awt.Color(242, 242, 242));

        panelLegend.setBackground(new java.awt.Color(204, 204, 204));
        panelLegend.setOpaque(false);
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 153, 255));
        jLabel1.setText("Thống Kê Bệnh Nhân Theo Ngày Trong Tuần");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
            .addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelBorder2.setBackground(new java.awt.Color(242, 242, 242));

        panelLegendPie.setBackground(new java.awt.Color(242, 242, 242));
        panelLegendPie.setLayout(new java.awt.GridLayout(0, 1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 153, 255));
        jLabel2.setText("Thống Kê Bệnh Nhân");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 153, 255));
        jLabel3.setText("Theo Quê Quán");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(panelLegendPie, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelLegendPie, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.noitru.swing.blankchart.BlankPlotChart blankPlotChart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.noitru.swing.PanelBorder panelBorder1;
    private com.noitru.swing.PanelBorder panelBorder2;
    private javax.swing.JPanel panelLegend;
    private javax.swing.JPanel panelLegendPie;
    private com.noitru.swing.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
