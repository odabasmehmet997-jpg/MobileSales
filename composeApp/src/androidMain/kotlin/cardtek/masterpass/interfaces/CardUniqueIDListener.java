package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.results.CardUniqueIDResult;

public interface CardUniqueIDListener {
    void onInternalError(InternalError internalError);
    void onServiceError(CardUniqueIDResult serviceError);
    void onSuccess(CardUniqueIDResult cardUniqueIDResult);
}
