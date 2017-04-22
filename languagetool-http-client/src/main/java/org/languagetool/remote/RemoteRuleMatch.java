/* LanguageTool, a natural language style checker
 * Copyright (C) 2016 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.remote;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A potential error as returned by the HTTP API of LanguageTool.
 * @since 3.4
 */
public class RemoteRuleMatch {

  private final String ruleId;
  private final String msg;
  private final String context;
  private final int contextOffset;
  private final int offset;
  private final int errorLength;

  private String subId;
  private String shortMsg;
  private List<String> replacements;
  private String url;
  private String category;
  private String categoryId;
  private String locQualityIssueType;

  RemoteRuleMatch(String ruleId, String msg, String context, int contextOffset, int offset, int errorLength) {
    this.ruleId = Objects.requireNonNull(ruleId);
    this.msg = Objects.requireNonNull(msg);
    this.context = Objects.requireNonNull(context);
    this.contextOffset = contextOffset;
    this.offset = offset;
    this.errorLength = errorLength;
  }

  /** Unique (per language) identifier for the error. */
  public String getRuleId() {
    return ruleId;
  }

  /** Optional sub id (rule groups have a sub id for each rule). */
  public String getRuleSubId() {
    return subId;
  }

  /** A text describing the error to the user. */
  public String getMessage() {
    return msg;
  }

  /** Optional short message describing the error. */
  public String getShortMessage() {
    return shortMsg;
  }

  /**
   * Potential corrections for the error. Note that corrections might be wrong and
   * they are not necessarily ordered by quality.
   */
  public List<String> getReplacements() {
    return replacements;
  }

  /** The error in its context. See {@link #getContextOffset()} and {@link #getErrorLength()} to get the exact position. */
  public String getContext() {
    return context;
  }
  
  /** The character position of the error start inside the result of {@link #getContext()}. */
  public int getContextOffset() {
    return contextOffset;
  }
  
  /** The character position where the error starts. */
  public int getErrorOffset() {
    return offset;
  }
  
  /** The length of the error in characters. */
  public int getErrorLength() {
    return errorLength;
  }

  /** URL with a more detailed explanation of the error. */
  public String getUrl() {
    return url;
  }

  /** The error's category. */
  public String getCategory() {
    return category;
  }

  /** The id of the error's category. */
  public String getCategoryId() {
    return categoryId;
  }

  public String getLocQualityIssueType() {
    return locQualityIssueType;
  }

  //
  // non-public setters
  //
  
  void setRuleSubId(String subId) {
    this.subId = subId;
  }

  void setShortMsg(String shortMsg) {
    this.shortMsg = shortMsg;
  }

  void setReplacements(List<String> replacements) {
    this.replacements = Collections.unmodifiableList(replacements);
  }

  void setUrl(String url) {
    this.url = url;
  }

  void setCategory(String category) {
    this.category = category;
  }

  void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  void setLocQualityIssueType(String locQualityIssueType) {
    this.locQualityIssueType = locQualityIssueType;
  }

  @Override
  public String toString() {
    return ruleId + "@" + offset + "-" + (offset + errorLength);
  }
}
