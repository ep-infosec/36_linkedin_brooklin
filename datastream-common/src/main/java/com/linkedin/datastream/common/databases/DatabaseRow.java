/**
 *  Copyright 2019 LinkedIn Corporation. All rights reserved.
 *  Licensed under the BSD 2-Clause License. See the LICENSE file in the project root for license information.
 *  See the NOTICE file in the project root for additional information regarding copyright ownership.
 */
package com.linkedin.datastream.common.databases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.linkedin.datastream.common.DatastreamRuntimeException;


/**
 * Represents a single row of a Database and composed of a list of DatabaseColumnRecords for each field
 */
public class DatabaseRow {
  private List<DatabaseColumnRecord> _allFields = new ArrayList<>();

  /**
   * Construct a DatabaseRow using a list of fields/columns
   */
  public DatabaseRow(List<DatabaseColumnRecord> fields) {
    _allFields = fields;
  }

  /**
   * Construct a DatabaseRow with no fields/columns
   */
  public DatabaseRow() {
  }

  /**
   * Get the number of fields/columns in the row
   */
  public int getColumnCount() {
    return _allFields.size();
  }

  /**
   * Add fields to the row record.
   * @param colName Field Name in the database
   * @param val Value for the field
   * @param sqlType Type of value in the field
   */
  public DatabaseRow addField(String colName, Object val, int sqlType) throws DatastreamRuntimeException {
    DatabaseColumnRecord columnRecord = new DatabaseColumnRecord(colName, val, sqlType);
    try {
      _allFields.add(columnRecord);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  /**
   * Get all records in the row
   */
  public List<DatabaseColumnRecord> getRecords() {
    return _allFields;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    _allFields.forEach(column -> str.append(column.toString() + ","));
    return str.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof DatabaseRow)) {
      return false;
    }
    DatabaseRow that = (DatabaseRow) o;
    if (this.getColumnCount() != that.getColumnCount()) {
      return false;
    }

    Iterator<DatabaseColumnRecord> thisIter = this._allFields.iterator();
    Iterator<DatabaseColumnRecord> thatIter = that._allFields.iterator();
    while (thisIter.hasNext()) {
      if (!thisIter.next().equals(thatIter.next())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }
}