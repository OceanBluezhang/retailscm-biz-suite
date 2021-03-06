
package com.doublechaintech.retailscm.accountingdocumentconfirmation;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.retailscm.BaseDAO;
import com.doublechaintech.retailscm.BaseEntity;
import com.doublechaintech.retailscm.SmartList;
import com.doublechaintech.retailscm.MultipleAccessKey;
import com.doublechaintech.retailscm.RetailscmUserContext;

import com.doublechaintech.retailscm.accountingdocument.AccountingDocument;

import com.doublechaintech.retailscm.accountingdocument.AccountingDocumentDAO;


public interface AccountingDocumentConfirmationDAO extends BaseDAO{

	public SmartList<AccountingDocumentConfirmation> loadAll();
	public AccountingDocumentConfirmation load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<AccountingDocumentConfirmation> accountingDocumentConfirmationList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public AccountingDocumentConfirmation present(AccountingDocumentConfirmation accountingDocumentConfirmation,Map<String,Object> options) throws Exception;
	public AccountingDocumentConfirmation clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public AccountingDocumentConfirmation save(AccountingDocumentConfirmation accountingDocumentConfirmation,Map<String,Object> options);
	public SmartList<AccountingDocumentConfirmation> saveAccountingDocumentConfirmationList(SmartList<AccountingDocumentConfirmation> accountingDocumentConfirmationList,Map<String,Object> options);
	public SmartList<AccountingDocumentConfirmation> removeAccountingDocumentConfirmationList(SmartList<AccountingDocumentConfirmation> accountingDocumentConfirmationList,Map<String,Object> options);
	public SmartList<AccountingDocumentConfirmation> findAccountingDocumentConfirmationWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countAccountingDocumentConfirmationWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countAccountingDocumentConfirmationWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String accountingDocumentConfirmationId, int version) throws Exception;
	public AccountingDocumentConfirmation disconnectFromAll(String accountingDocumentConfirmationId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public AccountingDocumentDAO getAccountingDocumentDAO();
		
	
 	public SmartList<AccountingDocumentConfirmation> requestCandidateAccountingDocumentConfirmationForAccountingDocument(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public AccountingDocumentConfirmation planToRemoveAccountingDocumentList(AccountingDocumentConfirmation accountingDocumentConfirmation, String accountingDocumentIds[], Map<String,Object> options)throws Exception;


	//disconnect AccountingDocumentConfirmation with accounting_period in AccountingDocument
	public AccountingDocumentConfirmation planToRemoveAccountingDocumentListWithAccountingPeriod(AccountingDocumentConfirmation accountingDocumentConfirmation, String accountingPeriodId, Map<String,Object> options)throws Exception;
	public int countAccountingDocumentListWithAccountingPeriod(String accountingDocumentConfirmationId, String accountingPeriodId, Map<String,Object> options)throws Exception;
	
	//disconnect AccountingDocumentConfirmation with document_type in AccountingDocument
	public AccountingDocumentConfirmation planToRemoveAccountingDocumentListWithDocumentType(AccountingDocumentConfirmation accountingDocumentConfirmation, String documentTypeId, Map<String,Object> options)throws Exception;
	public int countAccountingDocumentListWithDocumentType(String accountingDocumentConfirmationId, String documentTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect AccountingDocumentConfirmation with creation in AccountingDocument
	public AccountingDocumentConfirmation planToRemoveAccountingDocumentListWithCreation(AccountingDocumentConfirmation accountingDocumentConfirmation, String creationId, Map<String,Object> options)throws Exception;
	public int countAccountingDocumentListWithCreation(String accountingDocumentConfirmationId, String creationId, Map<String,Object> options)throws Exception;
	
	//disconnect AccountingDocumentConfirmation with auditing in AccountingDocument
	public AccountingDocumentConfirmation planToRemoveAccountingDocumentListWithAuditing(AccountingDocumentConfirmation accountingDocumentConfirmation, String auditingId, Map<String,Object> options)throws Exception;
	public int countAccountingDocumentListWithAuditing(String accountingDocumentConfirmationId, String auditingId, Map<String,Object> options)throws Exception;
	
	//disconnect AccountingDocumentConfirmation with posting in AccountingDocument
	public AccountingDocumentConfirmation planToRemoveAccountingDocumentListWithPosting(AccountingDocumentConfirmation accountingDocumentConfirmation, String postingId, Map<String,Object> options)throws Exception;
	public int countAccountingDocumentListWithPosting(String accountingDocumentConfirmationId, String postingId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<AccountingDocumentConfirmation> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);

	// 需要一个加载引用我的对象的enhance方法:AccountingDocument的confirmation的AccountingDocumentList
	public SmartList<AccountingDocument> loadOurAccountingDocumentList(RetailscmUserContext userContext, List<AccountingDocumentConfirmation> us, Map<String,Object> options) throws Exception;
	
}


