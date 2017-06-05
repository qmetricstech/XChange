package org.knowm.xchange.bitstamp.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitstamp.BitstampAdapters;
import org.knowm.xchange.bitstamp.dto.account.BitstampDepositAddress;
import org.knowm.xchange.bitstamp.dto.account.BitstampWithdrawal;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParamOffset;
import org.knowm.xchange.service.trade.params.TradeHistoryParamPaging;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsSorted;

/**
 * @author Matija Mazi
 */
public class BitstampAccountService extends BitstampAccountServiceRaw implements AccountService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitstampAccountService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    return BitstampAdapters.adaptAccountInfo(getBitstampBalance(), exchange.getExchangeSpecification().getUserName());
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {

    final BitstampWithdrawal response = withdrawBitstampFunds(currency, amount, address);
    if (response.getId() == null) {
      return null;
    }
    return Integer.toString(response.getId());
  }

  /**
   * This returns the currently set deposit address. It will not generate a new address (ie. repeated calls will return the same address).
   */
  @Override
  public String requestDepositAddress(Currency currency, String... arguments) throws IOException {

    final BitstampDepositAddress response = getBitstampBitcoinDepositAddress();
    return response.getDepositAddress();

  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {
      return new BitstampTradeHistoryParams(null, 1000);
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params)
          throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    
      
      Long limit = null;
      CurrencyPair currencyPair = null;
      Long offset = null;
      TradeHistoryParamsSorted.Order sort = null;
      if (params instanceof TradeHistoryParamPaging) {
        limit = Long.valueOf(((TradeHistoryParamPaging) params).getPageLength());
      }
      if (params instanceof TradeHistoryParamCurrencyPair) {
        currencyPair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
      }
      if (params instanceof TradeHistoryParamOffset) {
        offset = ((TradeHistoryParamOffset) params).getOffset();
      }
      if (params instanceof TradeHistoryParamsSorted) {
        sort = ((TradeHistoryParamsSorted) params).getOrder();
      }
      //BitstampUserTransaction[] txs = getBitstampUserTransactions(limit, currencyPair, offset, sort == null ? null : sort.toString());
      //return BitstampAdapters.adaptTradeHistory(txs);
      getBitstampUserTransactions(limit, currencyPair, offset, sort == null ? null : sort.toString());
      return null;
      
  }
}
