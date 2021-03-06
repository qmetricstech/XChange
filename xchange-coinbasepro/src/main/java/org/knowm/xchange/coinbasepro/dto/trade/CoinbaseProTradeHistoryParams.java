package org.knowm.xchange.coinbasepro.dto.trade;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParamTransactionId;

public class CoinbaseProTradeHistoryParams
    implements TradeHistoryParamTransactionId, TradeHistoryParamCurrencyPair {

  private CurrencyPair currencyPair;
  private String txId;
  private Integer afterTradeId;
  private Integer beforeTradeId;

  public Integer getAfterTradeId() {
    return afterTradeId;
  }

  public void setAfterTradeId(Integer startingOrderId) {
    this.afterTradeId = startingOrderId;
  }

  public Integer getBeforeTradeId() {
    return beforeTradeId;
  }

  public void setBeforeTradeId(Integer beforeTradeId) {
    this.beforeTradeId = beforeTradeId;
  }

  @Override
  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  @Override
  public void setCurrencyPair(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  @Override
  public String getTransactionId() {
    return txId;
  }

  @Override
  public void setTransactionId(String txId) {
    this.txId = txId;
  }
}
