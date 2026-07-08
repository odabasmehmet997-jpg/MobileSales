package com.proje.mobilesales.core.reportparser;



public class ReportChartDiagram {
    private ReportChartDiagramAxis mAxisX;
    private ReportChartDiagramAxis mAxisY;
    private String mDimension;
    private String mTypeNameSerializable;

    public String getDimension() {
        return mDimension;
    }

    public void setDimension(final String str) {
        mDimension = str;
    }

    public String getTypeNameSerializable() {
        return mTypeNameSerializable;
    }

    public void setTypeNameSerializable(final String str) {
        mTypeNameSerializable = str;
    }

    public ReportChartDiagramAxis getAxisX() {
        return mAxisX;
    }

    public void setAxisX(final ReportChartDiagramAxis reportChartDiagramAxis) {
        mAxisX = reportChartDiagramAxis;
    }

    public ReportChartDiagramAxis getAxisY() {
        return mAxisY;
    }

    public void setAxisY(final ReportChartDiagramAxis reportChartDiagramAxis) {
        mAxisY = reportChartDiagramAxis;
    }
}
