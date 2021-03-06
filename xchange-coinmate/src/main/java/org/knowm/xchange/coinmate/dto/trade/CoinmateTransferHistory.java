package org.knowm.xchange.coinmate.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.coinmate.dto.CoinmateBaseResponse;

import java.util.ArrayList;

public class CoinmateTransferHistory extends CoinmateBaseResponse<ArrayList<CoinmateTransferHistoryEntry>> {

  public CoinmateTransferHistory(
      @JsonProperty("error") boolean error,
      @JsonProperty("errorMessage") String errorMessage,
      @JsonProperty("data") ArrayList<CoinmateTransferHistoryEntry> data) {

    super(error, errorMessage, data);
  }
}
