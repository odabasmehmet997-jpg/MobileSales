package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CombinedChartRenderer extends com.github.mikephil.charting.renderer.DataRenderer {
    protected WeakReference<Chart> mChart;
    protected List<com.github.mikephil.charting.renderer.DataRenderer> mRenderers;

    public CombinedChartRenderer(CombinedChart combinedChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = new WeakReference<> (combinedChart);
        createRenderers(combinedChart, chartAnimator, viewPortHandler);
    }

    protected void createRenderers(CombinedChart combinedChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        this.mRenderers = new ArrayList ();
        for (CombinedChart.DrawOrder drawOrder : combinedChart.getDrawOrder ()) {
            int i = C12351.f822x2dab6d3b[drawOrder.ordinal ()];
            if (1 != i) {
                if (2 != i) {
                    if (3 != i) {
                        if (4 != i) {
                            if (5 == i && null != combinedChart.getScatterData ()) {
                                this.mRenderers.add (new com.github.mikephil.charting.renderer.ScatterChartRenderer (combinedChart, chartAnimator, viewPortHandler));
                            }
                        } else if (null != combinedChart.getCandleData ()) {
                            this.mRenderers.add (new CandleStickChartRenderer (combinedChart, chartAnimator, viewPortHandler));
                        }
                    } else if (null != combinedChart.getLineData ()) {
                        this.mRenderers.add (new com.github.mikephil.charting.renderer.LineChartRenderer (combinedChart, chartAnimator, viewPortHandler));
                    }
                } else if (null != combinedChart.getBubbleData ()) {
                    this.mRenderers.add (new com.github.mikephil.charting.renderer.BubbleChartRenderer (combinedChart, chartAnimator, viewPortHandler));
                }
            } else if (null != combinedChart.getBarData ()) {
                this.mRenderers.add (new com.github.mikephil.charting.renderer.BarChartRenderer (combinedChart, chartAnimator, viewPortHandler));
            }
        }
    }

    public void initBuffers() {
        for (com.github.mikephil.charting.renderer.DataRenderer dataRenderer : this.mRenderers) {
            dataRenderer.initBuffers ();
        }
    }

    public void drawData(Canvas canvas) {
        for (com.github.mikephil.charting.renderer.DataRenderer dataRenderer : this.mRenderers) {
            dataRenderer.drawData (canvas);
        }
    }

    public void drawValues(Canvas canvas) {
        for (com.github.mikephil.charting.renderer.DataRenderer dataRenderer : this.mRenderers) {
            dataRenderer.drawValues (canvas);
        }
    }

    public void drawExtras(Canvas canvas) {
        for (com.github.mikephil.charting.renderer.DataRenderer dataRenderer : this.mRenderers) {
            dataRenderer.drawExtras (canvas);
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        Object obj;
        int i;
        Chart chart = this.mChart.get ();
        if (null != chart) {
            for (com.github.mikephil.charting.renderer.DataRenderer dataRenderer : this.mRenderers) {
                if (dataRenderer instanceof com.github.mikephil.charting.renderer.BarChartRenderer) {
                    obj = ((BarChartRenderer) dataRenderer).mChart.getBarData ();
                } else if (dataRenderer instanceof com.github.mikephil.charting.renderer.LineChartRenderer) {
                    obj = ((LineChartRenderer) dataRenderer).mChart.getLineData ();
                } else if (dataRenderer instanceof CandleStickChartRenderer) {
                    obj = ((CandleStickChartRenderer) dataRenderer).mChart.getCandleData ();
                } else if (dataRenderer instanceof com.github.mikephil.charting.renderer.ScatterChartRenderer) {
                    obj = ((ScatterChartRenderer) dataRenderer).mChart.getScatterData ();
                } else {
                    obj = dataRenderer instanceof com.github.mikephil.charting.renderer.BubbleChartRenderer ? ((BubbleChartRenderer) dataRenderer).mChart.getBubbleData () : null;
                }
                if (null == obj) {
                    i = -1;
                } else {
                    i = ((CombinedData) chart.getData ()).getAllData ().indexOf (obj);
                }
                ArrayList arrayList = new ArrayList ();
                for (Highlight highlight : highlightArr) {
                    if (highlight.getDataIndex () == i || -1 == highlight.getDataIndex ()) {
                        arrayList.add (highlight);
                    }
                }
                dataRenderer.drawHighlighted (canvas, (Highlight[]) arrayList.toArray (new Highlight[arrayList.size ()]));
            }
        }
    }

    public void calcXBounds(BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider, int i) {
        for (com.github.mikephil.charting.renderer.DataRenderer dataRenderer : this.mRenderers) {
            dataRenderer.calcXBounds (barLineScatterCandleBubbleDataProvider, i);
        }
    }

    public com.github.mikephil.charting.renderer.DataRenderer getSubRenderer(int i) {
        if (i >= this.mRenderers.size () || 0 > i) {
            return null;
        }
        return this.mRenderers.get (i);
    }

    public List<com.github.mikephil.charting.renderer.DataRenderer> getSubRenderers() {
        return this.mRenderers;
    }

    public void setSubRenderers(List<DataRenderer> list) {
        this.mRenderers = list;
    }

    public enum C12351 {
        ;
        static final int[] f822x2dab6d3b;

        static {
            int[] iArr = new int[CombinedChart.DrawOrder.values ().length];
            f822x2dab6d3b = iArr;
            try {
                iArr[CombinedChart.DrawOrder.BAR.ordinal ()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f822x2dab6d3b[CombinedChart.DrawOrder.BUBBLE.ordinal ()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f822x2dab6d3b[CombinedChart.DrawOrder.LINE.ordinal ()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f822x2dab6d3b[CombinedChart.DrawOrder.CANDLE.ordinal ()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f822x2dab6d3b[CombinedChart.DrawOrder.SCATTER.ordinal ()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
