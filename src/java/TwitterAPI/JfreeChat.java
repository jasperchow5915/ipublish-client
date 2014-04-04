/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TwitterAPI;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author lingjunqiu
 */
public class JfreeChat {

    /** 
     * @param args 
     */  
    public void pie(int ptweet,int ntweet,int neutweet)  
    {     
        //设置数据源   
        PieDataset mDataset = GetDataset(ptweet,ntweet,neutweet);   
        //建立图表  
        JFreeChart mChart = ChartFactory.createPieChart3D("Sentiment Analysis", mDataset, true, true, false);  
        //设置图表标题  
        mChart.setTitle(new TextTitle("Sentiment Analysis", new Font("黑体",Font.CENTER_BASELINE, 20)));  
        //设置Legend字体  
        mChart.getLegend().setItemFont(new Font("Cambria", Font.ROMAN_BASELINE, 15));  
          
        PiePlot3D mPiePlot = (PiePlot3D)mChart.getPlot();  
        //以默认方式显示百分比  
        //mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));  
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位  
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}", NumberFormat.getNumberInstance(), new DecimalFormat("0.0%")));  
        // 底部图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例   
        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));  
        //设置饼图标签字体  
        mPiePlot.setLabelFont(new Font("Cambria", Font.PLAIN, 15));  
        
        // 指定图片的透明度(0.0-1.0)   
        //mPiePlot.setBackgroundImageAlpha(0.0f);  
        // 指定显示的饼图上圆形(false)还椭圆形(true) 默认为true  
        mPiePlot.setCircular(false);   
        ////////选做设置///////  
        //设置饼图背景颜色，默认为LIGHT_GRAY  
        mPiePlot.setBackgroundPaint(Color.WHITE);  
        
        //内置对象显示图表  
//        ChartFrame mChartFrame = new ChartFrame("Sentiment Analysis", mChart);  
//        mChartFrame.pack();  
//        mChartFrame.setVisible(true);  
        FileOutputStream fos = null;     
        try { 
            fos = new FileOutputStream("/Users/lingjunqiu/NetBeansProjects/IpublishClient/web/images/sentiAnalysis.png"); 
            ChartUtilities.writeChartAsJPEG(fos, mChart, 800, 500); 
            fos.close(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        } 
          
    }  
    private PieDataset GetDataset(int ptweet,int ntweet,int neutweet)  
    {  
        DefaultPieDataset mDataset = new DefaultPieDataset();         
        mDataset.setValue(" Positive", ptweet);   
        mDataset.setValue(" Negative", ntweet);   
        mDataset.setValue(" Neutrality", neutweet);   
 
        return mDataset;  
    }  
}  
