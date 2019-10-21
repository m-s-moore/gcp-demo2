package com.ntconcepts.gcpdemo2

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions
import org.apache.beam.sdk.options.Default
import org.apache.beam.sdk.options.Description
import org.apache.beam.sdk.options.ValueProvider

interface Demo2Options : DataflowPipelineOptions {

    val trainDataSource: ValueProvider<String>
    fun setTrainDataSource(trainDataSource: ValueProvider<String>)

    val testDataSource: ValueProvider<String>
    fun setTestDataSource(testDataSource: ValueProvider<String>)

    @get:Description("Bigquery output dataset")
    @get:Default.String("blackfriday")
    val outputDataset: ValueProvider<String>

    fun setOutputDataset(dataset: ValueProvider<String>)

    @get:Description("Bigquery purchases output table")
    @get:Default.String("purchases")
    val outputPurchaseTable: ValueProvider<String>

    fun setOutputPurchaseTable(table: ValueProvider<String>)

    @get:Description("Bigquery purchases output tablespec. Example: project_id:dataset.table")
    @get:Default.String("blackfriday.purchases")
    val outputPurchaseTableSpec: ValueProvider<String>

    fun setOutputPurchaseTableSpec(outputTableSpec: ValueProvider<String>)

    @get:Description("Bigquery user summary output table")
    @get:Default.String("user_summaries")
    val outputUserSummaryTable: ValueProvider<String>

    fun setOutputUserSummaryTable(table: ValueProvider<String>)

    @get:Description("Bigquery user summary output tablespec. Example: project_id:dataset.table")
    @get:Default.String("blackfriday.user_summaries")
    val outputUserSummaryTableSpec: ValueProvider<String>

    fun setOutputUserSummaryTableSpec(outputTableSpec: ValueProvider<String>)

    @get:Description("Drop output table when job starts")
    @get:Default.Boolean(true)
    val dropTable: ValueProvider<Boolean>

    fun setDropTable(dropTable: ValueProvider<Boolean>)


//    val categoricalValues: ValueProvider<List<String>>

}