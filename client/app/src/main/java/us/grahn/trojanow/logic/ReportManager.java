package us.grahn.trojanow.logic;

import us.grahn.trojanow.data.Report;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.logic.Manager;

/**
 * A data manager for reports. Handles CRUD operations with the server.
 *
 * @us.grahn.class     ReportManager
 * @us.grahn.component ReportManager
 * @us.grahn.tier      Logic
 */
public class ReportManager extends Manager {

    /**
     * Creates a report on the server.
     *
     * @param report the report to create
     * @return the result of the transaction with the server
     */
    public Result create(Report report) {
        return null;
    }

    @Override
    public Result refresh() {
        return null;
    }

    @Override
    public Result getLastResult() {
        return null;
    }
}
