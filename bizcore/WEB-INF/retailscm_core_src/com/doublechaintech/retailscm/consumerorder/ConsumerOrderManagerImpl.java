
package com.doublechaintech.retailscm.consumerorder;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.retailscm.BaseEntity;


import com.doublechaintech.retailscm.Message;
import com.doublechaintech.retailscm.SmartList;
import com.doublechaintech.retailscm.MultipleAccessKey;

import com.doublechaintech.retailscm.RetailscmUserContext;
//import com.doublechaintech.retailscm.BaseManagerImpl;
import com.doublechaintech.retailscm.RetailscmCheckerManager;
import com.doublechaintech.retailscm.CustomRetailscmCheckerManager;

import com.doublechaintech.retailscm.supplyorderapproval.SupplyOrderApproval;
import com.doublechaintech.retailscm.consumerordershippinggroup.ConsumerOrderShippingGroup;
import com.doublechaintech.retailscm.retailstoremember.RetailStoreMember;
import com.doublechaintech.retailscm.supplyorderdelivery.SupplyOrderDelivery;
import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.consumerorderpriceadjustment.ConsumerOrderPriceAdjustment;
import com.doublechaintech.retailscm.consumerorderpaymentgroup.ConsumerOrderPaymentGroup;
import com.doublechaintech.retailscm.consumerorderlineitem.ConsumerOrderLineItem;
import com.doublechaintech.retailscm.retailstoremembergiftcardconsumerecord.RetailStoreMemberGiftCardConsumeRecord;
import com.doublechaintech.retailscm.supplyorderprocessing.SupplyOrderProcessing;
import com.doublechaintech.retailscm.supplyorderconfirmation.SupplyOrderConfirmation;
import com.doublechaintech.retailscm.supplyordershipment.SupplyOrderShipment;

import com.doublechaintech.retailscm.supplyorderapproval.CandidateSupplyOrderApproval;
import com.doublechaintech.retailscm.retailstoremember.CandidateRetailStoreMember;
import com.doublechaintech.retailscm.supplyorderdelivery.CandidateSupplyOrderDelivery;
import com.doublechaintech.retailscm.retailstore.CandidateRetailStore;
import com.doublechaintech.retailscm.supplyorderprocessing.CandidateSupplyOrderProcessing;
import com.doublechaintech.retailscm.supplyorderconfirmation.CandidateSupplyOrderConfirmation;
import com.doublechaintech.retailscm.supplyordershipment.CandidateSupplyOrderShipment;

import com.doublechaintech.retailscm.consumerorder.ConsumerOrder;
import com.doublechaintech.retailscm.retailstoremembergiftcard.RetailStoreMemberGiftCard;






public class ConsumerOrderManagerImpl extends CustomRetailscmCheckerManager implements ConsumerOrderManager {
	
	private static final String SERVICE_TYPE = "ConsumerOrder";
	@Override
	public ConsumerOrderDAO daoOf(RetailscmUserContext userContext) {
		return consumerOrderDaoOf(userContext);
	}
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws ConsumerOrderManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new ConsumerOrderManagerException(message);

	}
	
	

 	protected ConsumerOrder saveConsumerOrder(RetailscmUserContext userContext, ConsumerOrder consumerOrder, String [] tokensExpr) throws Exception{	
 		//return getConsumerOrderDAO().save(consumerOrder, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveConsumerOrder(userContext, consumerOrder, tokens);
 	}
 	
 	protected ConsumerOrder saveConsumerOrderDetail(RetailscmUserContext userContext, ConsumerOrder consumerOrder) throws Exception{	

 		
 		return saveConsumerOrder(userContext, consumerOrder, allTokens());
 	}
 	
 	public ConsumerOrder loadConsumerOrder(RetailscmUserContext userContext, String consumerOrderId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).throwExceptionIfHasErrors( ConsumerOrderManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ConsumerOrder consumerOrder = loadConsumerOrder( userContext, consumerOrderId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,consumerOrder, tokens);
 	}
 	
 	
 	 public ConsumerOrder searchConsumerOrder(RetailscmUserContext userContext, String consumerOrderId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).throwExceptionIfHasErrors( ConsumerOrderManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ConsumerOrder consumerOrder = loadConsumerOrder( userContext, consumerOrderId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,consumerOrder, tokens);
 	}
 	
 	

 	protected ConsumerOrder present(RetailscmUserContext userContext, ConsumerOrder consumerOrder, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,consumerOrder,tokens);
		
		
		ConsumerOrder  consumerOrderToPresent = consumerOrderDaoOf(userContext).present(consumerOrder, tokens);
		
		List<BaseEntity> entityListToNaming = consumerOrderToPresent.collectRefercencesFromLists();
		consumerOrderDaoOf(userContext).alias(entityListToNaming);
		
		return  consumerOrderToPresent;
		
		
	}
 
 	
 	
 	public ConsumerOrder loadConsumerOrderDetail(RetailscmUserContext userContext, String consumerOrderId) throws Exception{	
 		ConsumerOrder consumerOrder = loadConsumerOrder( userContext, consumerOrderId, allTokens());
 		return present(userContext,consumerOrder, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String consumerOrderId) throws Exception{	
 		ConsumerOrder consumerOrder = loadConsumerOrder( userContext, consumerOrderId, viewTokens());
 		return present(userContext,consumerOrder, allTokens());
		
 	}
 	protected ConsumerOrder saveConsumerOrder(RetailscmUserContext userContext, ConsumerOrder consumerOrder, Map<String,Object>tokens) throws Exception{	
 		return consumerOrderDaoOf(userContext).save(consumerOrder, tokens);
 	}
 	protected ConsumerOrder loadConsumerOrder(RetailscmUserContext userContext, String consumerOrderId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).throwExceptionIfHasErrors( ConsumerOrderManagerException.class);

 
 		return consumerOrderDaoOf(userContext).load(consumerOrderId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, ConsumerOrder consumerOrder, Map<String, Object> tokens){
		super.addActions(userContext, consumerOrder, tokens);
		
		addAction(userContext, consumerOrder, tokens,"@create","createConsumerOrder","createConsumerOrder/","main","primary");
		addAction(userContext, consumerOrder, tokens,"@update","updateConsumerOrder","updateConsumerOrder/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"@copy","cloneConsumerOrder","cloneConsumerOrder/"+consumerOrder.getId()+"/","main","primary");
		
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_consumer","transferToAnotherConsumer","transferToAnotherConsumer/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_confirmation","transferToAnotherConfirmation","transferToAnotherConfirmation/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_approval","transferToAnotherApproval","transferToAnotherApproval/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_processing","transferToAnotherProcessing","transferToAnotherProcessing/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_shipment","transferToAnotherShipment","transferToAnotherShipment/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_delivery","transferToAnotherDelivery","transferToAnotherDelivery/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.transfer_to_store","transferToAnotherStore","transferToAnotherStore/"+consumerOrder.getId()+"/","main","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.addConsumerOrderLineItem","addConsumerOrderLineItem","addConsumerOrderLineItem/"+consumerOrder.getId()+"/","consumerOrderLineItemList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.removeConsumerOrderLineItem","removeConsumerOrderLineItem","removeConsumerOrderLineItem/"+consumerOrder.getId()+"/","consumerOrderLineItemList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.updateConsumerOrderLineItem","updateConsumerOrderLineItem","updateConsumerOrderLineItem/"+consumerOrder.getId()+"/","consumerOrderLineItemList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.copyConsumerOrderLineItemFrom","copyConsumerOrderLineItemFrom","copyConsumerOrderLineItemFrom/"+consumerOrder.getId()+"/","consumerOrderLineItemList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.addConsumerOrderShippingGroup","addConsumerOrderShippingGroup","addConsumerOrderShippingGroup/"+consumerOrder.getId()+"/","consumerOrderShippingGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.removeConsumerOrderShippingGroup","removeConsumerOrderShippingGroup","removeConsumerOrderShippingGroup/"+consumerOrder.getId()+"/","consumerOrderShippingGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.updateConsumerOrderShippingGroup","updateConsumerOrderShippingGroup","updateConsumerOrderShippingGroup/"+consumerOrder.getId()+"/","consumerOrderShippingGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.copyConsumerOrderShippingGroupFrom","copyConsumerOrderShippingGroupFrom","copyConsumerOrderShippingGroupFrom/"+consumerOrder.getId()+"/","consumerOrderShippingGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.addConsumerOrderPaymentGroup","addConsumerOrderPaymentGroup","addConsumerOrderPaymentGroup/"+consumerOrder.getId()+"/","consumerOrderPaymentGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.removeConsumerOrderPaymentGroup","removeConsumerOrderPaymentGroup","removeConsumerOrderPaymentGroup/"+consumerOrder.getId()+"/","consumerOrderPaymentGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.updateConsumerOrderPaymentGroup","updateConsumerOrderPaymentGroup","updateConsumerOrderPaymentGroup/"+consumerOrder.getId()+"/","consumerOrderPaymentGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.copyConsumerOrderPaymentGroupFrom","copyConsumerOrderPaymentGroupFrom","copyConsumerOrderPaymentGroupFrom/"+consumerOrder.getId()+"/","consumerOrderPaymentGroupList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.addConsumerOrderPriceAdjustment","addConsumerOrderPriceAdjustment","addConsumerOrderPriceAdjustment/"+consumerOrder.getId()+"/","consumerOrderPriceAdjustmentList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.removeConsumerOrderPriceAdjustment","removeConsumerOrderPriceAdjustment","removeConsumerOrderPriceAdjustment/"+consumerOrder.getId()+"/","consumerOrderPriceAdjustmentList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.updateConsumerOrderPriceAdjustment","updateConsumerOrderPriceAdjustment","updateConsumerOrderPriceAdjustment/"+consumerOrder.getId()+"/","consumerOrderPriceAdjustmentList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.copyConsumerOrderPriceAdjustmentFrom","copyConsumerOrderPriceAdjustmentFrom","copyConsumerOrderPriceAdjustmentFrom/"+consumerOrder.getId()+"/","consumerOrderPriceAdjustmentList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.addRetailStoreMemberGiftCardConsumeRecord","addRetailStoreMemberGiftCardConsumeRecord","addRetailStoreMemberGiftCardConsumeRecord/"+consumerOrder.getId()+"/","retailStoreMemberGiftCardConsumeRecordList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.removeRetailStoreMemberGiftCardConsumeRecord","removeRetailStoreMemberGiftCardConsumeRecord","removeRetailStoreMemberGiftCardConsumeRecord/"+consumerOrder.getId()+"/","retailStoreMemberGiftCardConsumeRecordList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.updateRetailStoreMemberGiftCardConsumeRecord","updateRetailStoreMemberGiftCardConsumeRecord","updateRetailStoreMemberGiftCardConsumeRecord/"+consumerOrder.getId()+"/","retailStoreMemberGiftCardConsumeRecordList","primary");
		addAction(userContext, consumerOrder, tokens,"consumer_order.copyRetailStoreMemberGiftCardConsumeRecordFrom","copyRetailStoreMemberGiftCardConsumeRecordFrom","copyRetailStoreMemberGiftCardConsumeRecordFrom/"+consumerOrder.getId()+"/","retailStoreMemberGiftCardConsumeRecordList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, ConsumerOrder consumerOrder, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ConsumerOrder createConsumerOrder(RetailscmUserContext userContext, String title,String consumerId,String confirmationId,String approvalId,String processingId,String shipmentId,String deliveryId,String storeId) throws Exception
	//public ConsumerOrder createConsumerOrder(RetailscmUserContext userContext,String title, String consumerId, String confirmationId, String approvalId, String processingId, String shipmentId, String deliveryId, String storeId) throws Exception
	{
		
		

		

		checkerOf(userContext).checkTitleOfConsumerOrder(title);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);


		ConsumerOrder consumerOrder=createNewConsumerOrder();	

		consumerOrder.setTitle(title);
			
		RetailStoreMember consumer = loadRetailStoreMember(userContext, consumerId,emptyOptions());
		consumerOrder.setConsumer(consumer);
		
		
			
		SupplyOrderConfirmation confirmation = loadSupplyOrderConfirmation(userContext, confirmationId,emptyOptions());
		consumerOrder.setConfirmation(confirmation);
		
		
			
		SupplyOrderApproval approval = loadSupplyOrderApproval(userContext, approvalId,emptyOptions());
		consumerOrder.setApproval(approval);
		
		
			
		SupplyOrderProcessing processing = loadSupplyOrderProcessing(userContext, processingId,emptyOptions());
		consumerOrder.setProcessing(processing);
		
		
			
		SupplyOrderShipment shipment = loadSupplyOrderShipment(userContext, shipmentId,emptyOptions());
		consumerOrder.setShipment(shipment);
		
		
			
		SupplyOrderDelivery delivery = loadSupplyOrderDelivery(userContext, deliveryId,emptyOptions());
		consumerOrder.setDelivery(delivery);
		
		
			
		RetailStore store = loadRetailStore(userContext, storeId,emptyOptions());
		consumerOrder.setStore(store);
		
		
		consumerOrder.setLastUpdateTime(userContext.now());

		consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
		
		onNewInstanceCreated(userContext, consumerOrder);
		return consumerOrder;

		
	}
	protected ConsumerOrder createNewConsumerOrder() 
	{
		
		return new ConsumerOrder();		
	}
	
	protected void checkParamsForUpdatingConsumerOrder(RetailscmUserContext userContext,String consumerOrderId, int consumerOrderVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkVersionOfConsumerOrder( consumerOrderVersion);
		

		if(ConsumerOrder.TITLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkTitleOfConsumerOrder(parseString(newValueExpr));
		}		

				

				

				

				

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
		
	}
	
	
	
	public ConsumerOrder clone(RetailscmUserContext userContext, String fromConsumerOrderId) throws Exception{
		
		return consumerOrderDaoOf(userContext).clone(fromConsumerOrderId, this.allTokens());
	}
	
	public ConsumerOrder internalSaveConsumerOrder(RetailscmUserContext userContext, ConsumerOrder consumerOrder) throws Exception 
	{
		return internalSaveConsumerOrder(userContext, consumerOrder, allTokens());

	}
	public ConsumerOrder internalSaveConsumerOrder(RetailscmUserContext userContext, ConsumerOrder consumerOrder, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingConsumerOrder(userContext, consumerOrderId, consumerOrderVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(consumerOrder){ 
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ConsumerOrder.
			if (consumerOrder.isChanged()){
			consumerOrder.updateLastUpdateTime(userContext.now());
			}
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, options);
			return consumerOrder;
			
		}

	}
	
	public ConsumerOrder updateConsumerOrder(RetailscmUserContext userContext,String consumerOrderId, int consumerOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingConsumerOrder(userContext, consumerOrderId, consumerOrderVersion, property, newValueExpr, tokensExpr);
		
		
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		if(consumerOrder.getVersion() != consumerOrderVersion){
			String message = "The target version("+consumerOrder.getVersion()+") is not equals to version("+consumerOrderVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(consumerOrder){ 
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ConsumerOrder.
			consumerOrder.updateLastUpdateTime(userContext.now());
			consumerOrder.changeProperty(property, newValueExpr);
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			//return saveConsumerOrder(userContext, consumerOrder, tokens().done());
		}

	}
	
	public ConsumerOrder updateConsumerOrderProperty(RetailscmUserContext userContext,String consumerOrderId, int consumerOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingConsumerOrder(userContext, consumerOrderId, consumerOrderVersion, property, newValueExpr, tokensExpr);
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		if(consumerOrder.getVersion() != consumerOrderVersion){
			String message = "The target version("+consumerOrder.getVersion()+") is not equals to version("+consumerOrderVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(consumerOrder){ 
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ConsumerOrder.
			
			consumerOrder.changeProperty(property, newValueExpr);
			consumerOrder.updateLastUpdateTime(userContext.now());
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			//return saveConsumerOrder(userContext, consumerOrder, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected ConsumerOrderTokens tokens(){
		return ConsumerOrderTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ConsumerOrderTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortConsumerOrderLineItemListWith("id","desc")
		.sortConsumerOrderShippingGroupListWith("id","desc")
		.sortConsumerOrderPaymentGroupListWith("id","desc")
		.sortConsumerOrderPriceAdjustmentListWith("id","desc")
		.sortRetailStoreMemberGiftCardConsumeRecordListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ConsumerOrderTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherConsumer(RetailscmUserContext userContext, String consumerOrderId, String anotherConsumerId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfRetailStoreMember(anotherConsumerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherConsumer(RetailscmUserContext userContext, String consumerOrderId, String anotherConsumerId) throws Exception
 	{
 		checkParamsForTransferingAnotherConsumer(userContext, consumerOrderId,anotherConsumerId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreMember consumer = loadRetailStoreMember(userContext, anotherConsumerId, emptyOptions());		
			consumerOrder.updateConsumer(consumer);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateRetailStoreMember requestCandidateConsumer(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateRetailStoreMember result = new CandidateRetailStoreMember();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<RetailStoreMember> candidateList = retailStoreMemberDaoOf(userContext).requestCandidateRetailStoreMemberForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherConfirmation(RetailscmUserContext userContext, String consumerOrderId, String anotherConfirmationId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfSupplyOrderConfirmation(anotherConfirmationId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherConfirmation(RetailscmUserContext userContext, String consumerOrderId, String anotherConfirmationId) throws Exception
 	{
 		checkParamsForTransferingAnotherConfirmation(userContext, consumerOrderId,anotherConfirmationId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SupplyOrderConfirmation confirmation = loadSupplyOrderConfirmation(userContext, anotherConfirmationId, emptyOptions());		
			consumerOrder.updateConfirmation(confirmation);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateSupplyOrderConfirmation requestCandidateConfirmation(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSupplyOrderConfirmation result = new CandidateSupplyOrderConfirmation();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("who");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SupplyOrderConfirmation> candidateList = supplyOrderConfirmationDaoOf(userContext).requestCandidateSupplyOrderConfirmationForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherApproval(RetailscmUserContext userContext, String consumerOrderId, String anotherApprovalId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfSupplyOrderApproval(anotherApprovalId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherApproval(RetailscmUserContext userContext, String consumerOrderId, String anotherApprovalId) throws Exception
 	{
 		checkParamsForTransferingAnotherApproval(userContext, consumerOrderId,anotherApprovalId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SupplyOrderApproval approval = loadSupplyOrderApproval(userContext, anotherApprovalId, emptyOptions());		
			consumerOrder.updateApproval(approval);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateSupplyOrderApproval requestCandidateApproval(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSupplyOrderApproval result = new CandidateSupplyOrderApproval();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("who");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SupplyOrderApproval> candidateList = supplyOrderApprovalDaoOf(userContext).requestCandidateSupplyOrderApprovalForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherProcessing(RetailscmUserContext userContext, String consumerOrderId, String anotherProcessingId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfSupplyOrderProcessing(anotherProcessingId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherProcessing(RetailscmUserContext userContext, String consumerOrderId, String anotherProcessingId) throws Exception
 	{
 		checkParamsForTransferingAnotherProcessing(userContext, consumerOrderId,anotherProcessingId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SupplyOrderProcessing processing = loadSupplyOrderProcessing(userContext, anotherProcessingId, emptyOptions());		
			consumerOrder.updateProcessing(processing);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateSupplyOrderProcessing requestCandidateProcessing(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSupplyOrderProcessing result = new CandidateSupplyOrderProcessing();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("who");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SupplyOrderProcessing> candidateList = supplyOrderProcessingDaoOf(userContext).requestCandidateSupplyOrderProcessingForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherShipment(RetailscmUserContext userContext, String consumerOrderId, String anotherShipmentId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfSupplyOrderShipment(anotherShipmentId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherShipment(RetailscmUserContext userContext, String consumerOrderId, String anotherShipmentId) throws Exception
 	{
 		checkParamsForTransferingAnotherShipment(userContext, consumerOrderId,anotherShipmentId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SupplyOrderShipment shipment = loadSupplyOrderShipment(userContext, anotherShipmentId, emptyOptions());		
			consumerOrder.updateShipment(shipment);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateSupplyOrderShipment requestCandidateShipment(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSupplyOrderShipment result = new CandidateSupplyOrderShipment();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("who");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SupplyOrderShipment> candidateList = supplyOrderShipmentDaoOf(userContext).requestCandidateSupplyOrderShipmentForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherDelivery(RetailscmUserContext userContext, String consumerOrderId, String anotherDeliveryId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfSupplyOrderDelivery(anotherDeliveryId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherDelivery(RetailscmUserContext userContext, String consumerOrderId, String anotherDeliveryId) throws Exception
 	{
 		checkParamsForTransferingAnotherDelivery(userContext, consumerOrderId,anotherDeliveryId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SupplyOrderDelivery delivery = loadSupplyOrderDelivery(userContext, anotherDeliveryId, emptyOptions());		
			consumerOrder.updateDelivery(delivery);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateSupplyOrderDelivery requestCandidateDelivery(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSupplyOrderDelivery result = new CandidateSupplyOrderDelivery();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("who");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SupplyOrderDelivery> candidateList = supplyOrderDeliveryDaoOf(userContext).requestCandidateSupplyOrderDeliveryForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherStore(RetailscmUserContext userContext, String consumerOrderId, String anotherStoreId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
 		checkerOf(userContext).checkIdOfRetailStore(anotherStoreId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
 		
 	}
 	public ConsumerOrder transferToAnotherStore(RetailscmUserContext userContext, String consumerOrderId, String anotherStoreId) throws Exception
 	{
 		checkParamsForTransferingAnotherStore(userContext, consumerOrderId,anotherStoreId);
 
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());	
		synchronized(consumerOrder){
			//will be good when the consumerOrder loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStore store = loadRetailStore(userContext, anotherStoreId, emptyOptions());		
			consumerOrder.updateStore(store);		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, emptyOptions());
			
			return present(userContext,consumerOrder, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateRetailStore requestCandidateStore(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateRetailStore result = new CandidateRetailStore();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<RetailStore> candidateList = retailStoreDaoOf(userContext).requestCandidateRetailStoreForConsumerOrder(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected RetailStoreMember loadRetailStoreMember(RetailscmUserContext userContext, String newConsumerId, Map<String,Object> options) throws Exception
 	{
		
 		return retailStoreMemberDaoOf(userContext).load(newConsumerId, options);
 	}
 	
 	
 	
	
	 	
 	protected SupplyOrderProcessing loadSupplyOrderProcessing(RetailscmUserContext userContext, String newProcessingId, Map<String,Object> options) throws Exception
 	{
		
 		return supplyOrderProcessingDaoOf(userContext).load(newProcessingId, options);
 	}
 	
 	
 	
	
	 	
 	protected SupplyOrderApproval loadSupplyOrderApproval(RetailscmUserContext userContext, String newApprovalId, Map<String,Object> options) throws Exception
 	{
		
 		return supplyOrderApprovalDaoOf(userContext).load(newApprovalId, options);
 	}
 	
 	
 	
	
	 	
 	protected RetailStore loadRetailStore(RetailscmUserContext userContext, String newStoreId, Map<String,Object> options) throws Exception
 	{
		
 		return retailStoreDaoOf(userContext).load(newStoreId, options);
 	}
 	
 	
 	
	
	 	
 	protected SupplyOrderDelivery loadSupplyOrderDelivery(RetailscmUserContext userContext, String newDeliveryId, Map<String,Object> options) throws Exception
 	{
		
 		return supplyOrderDeliveryDaoOf(userContext).load(newDeliveryId, options);
 	}
 	
 	
 	
	
	 	
 	protected SupplyOrderConfirmation loadSupplyOrderConfirmation(RetailscmUserContext userContext, String newConfirmationId, Map<String,Object> options) throws Exception
 	{
		
 		return supplyOrderConfirmationDaoOf(userContext).load(newConfirmationId, options);
 	}
 	
 	
 	
	
	 	
 	protected SupplyOrderShipment loadSupplyOrderShipment(RetailscmUserContext userContext, String newShipmentId, Map<String,Object> options) throws Exception
 	{
		
 		return supplyOrderShipmentDaoOf(userContext).load(newShipmentId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String consumerOrderId, int consumerOrderVersion) throws Exception {
		//deleteInternal(userContext, consumerOrderId, consumerOrderVersion);		
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String consumerOrderId, int consumerOrderVersion) throws Exception{
			
		consumerOrderDaoOf(userContext).delete(consumerOrderId, consumerOrderVersion);
	}
	
	public ConsumerOrder forgetByAll(RetailscmUserContext userContext, String consumerOrderId, int consumerOrderVersion) throws Exception {
		return forgetByAllInternal(userContext, consumerOrderId, consumerOrderVersion);		
	}
	protected ConsumerOrder forgetByAllInternal(RetailscmUserContext userContext,
			String consumerOrderId, int consumerOrderVersion) throws Exception{
			
		return consumerOrderDaoOf(userContext).disconnectFromAll(consumerOrderId, consumerOrderVersion);
	}
	
	

	
	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ConsumerOrderManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return consumerOrderDaoOf(userContext).deleteAll();
	}


	//disconnect ConsumerOrder with sku_id in ConsumerOrderLineItem
	protected ConsumerOrder breakWithConsumerOrderLineItemBySkuId(RetailscmUserContext userContext, String consumerOrderId, String skuIdId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());

			synchronized(consumerOrder){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				consumerOrderDaoOf(userContext).planToRemoveConsumerOrderLineItemListWithSkuId(consumerOrder, skuIdId, this.emptyOptions());

				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderLineItemList().done());
				return consumerOrder;
			}
	}
	//disconnect ConsumerOrder with owner in RetailStoreMemberGiftCardConsumeRecord
	protected ConsumerOrder breakWithRetailStoreMemberGiftCardConsumeRecordByOwner(RetailscmUserContext userContext, String consumerOrderId, String ownerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());

			synchronized(consumerOrder){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				consumerOrderDaoOf(userContext).planToRemoveRetailStoreMemberGiftCardConsumeRecordListWithOwner(consumerOrder, ownerId, this.emptyOptions());

				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
				return consumerOrder;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, String skuId, String skuName, BigDecimal price, BigDecimal quantity, BigDecimal amount,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);

		
		checkerOf(userContext).checkSkuIdOfConsumerOrderLineItem(skuId);
		
		checkerOf(userContext).checkSkuNameOfConsumerOrderLineItem(skuName);
		
		checkerOf(userContext).checkPriceOfConsumerOrderLineItem(price);
		
		checkerOf(userContext).checkQuantityOfConsumerOrderLineItem(quantity);
		
		checkerOf(userContext).checkAmountOfConsumerOrderLineItem(amount);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);

	
	}
	public  ConsumerOrder addConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, String skuId, String skuName, BigDecimal price, BigDecimal quantity, BigDecimal amount, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingConsumerOrderLineItem(userContext,consumerOrderId,skuId, skuName, price, quantity, amount,tokensExpr);
		
		ConsumerOrderLineItem consumerOrderLineItem = createConsumerOrderLineItem(userContext,skuId, skuName, price, quantity, amount);
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.addConsumerOrderLineItem( consumerOrderLineItem );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderLineItemList().done());
			
			userContext.getManagerGroup().getConsumerOrderLineItemManager().onNewInstanceCreated(userContext, consumerOrderLineItem);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingConsumerOrderLineItemProperties(RetailscmUserContext userContext, String consumerOrderId,String id,String skuId,String skuName,BigDecimal price,BigDecimal quantity,BigDecimal amount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderLineItem(id);
		
		checkerOf(userContext).checkSkuIdOfConsumerOrderLineItem( skuId);
		checkerOf(userContext).checkSkuNameOfConsumerOrderLineItem( skuName);
		checkerOf(userContext).checkPriceOfConsumerOrderLineItem( price);
		checkerOf(userContext).checkQuantityOfConsumerOrderLineItem( quantity);
		checkerOf(userContext).checkAmountOfConsumerOrderLineItem( amount);

		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder updateConsumerOrderLineItemProperties(RetailscmUserContext userContext, String consumerOrderId, String id,String skuId,String skuName,BigDecimal price,BigDecimal quantity,BigDecimal amount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingConsumerOrderLineItemProperties(userContext,consumerOrderId,id,skuId,skuName,price,quantity,amount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withConsumerOrderLineItemListList()
				.searchConsumerOrderLineItemListWith(ConsumerOrderLineItem.ID_PROPERTY, "is", id).done();
		
		ConsumerOrder consumerOrderToUpdate = loadConsumerOrder(userContext, consumerOrderId, options);
		
		if(consumerOrderToUpdate.getConsumerOrderLineItemList().isEmpty()){
			throw new ConsumerOrderManagerException("ConsumerOrderLineItem is NOT FOUND with id: '"+id+"'");
		}
		
		ConsumerOrderLineItem item = consumerOrderToUpdate.getConsumerOrderLineItemList().first();
		
		item.updateSkuId( skuId );
		item.updateSkuName( skuName );
		item.updatePrice( price );
		item.updateQuantity( quantity );
		item.updateAmount( amount );

		
		//checkParamsForAddingConsumerOrderLineItem(userContext,consumerOrderId,name, code, used,tokensExpr);
		ConsumerOrder consumerOrder = saveConsumerOrder(userContext, consumerOrderToUpdate, tokens().withConsumerOrderLineItemList().done());
		synchronized(consumerOrder){ 
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ConsumerOrderLineItem createConsumerOrderLineItem(RetailscmUserContext userContext, String skuId, String skuName, BigDecimal price, BigDecimal quantity, BigDecimal amount) throws Exception{

		ConsumerOrderLineItem consumerOrderLineItem = new ConsumerOrderLineItem();
		
		
		consumerOrderLineItem.setSkuId(skuId);		
		consumerOrderLineItem.setSkuName(skuName);		
		consumerOrderLineItem.setPrice(price);		
		consumerOrderLineItem.setQuantity(quantity);		
		consumerOrderLineItem.setAmount(amount);		
		consumerOrderLineItem.setLastUpdateTime(userContext.now());
	
		
		return consumerOrderLineItem;
	
		
	}
	
	protected ConsumerOrderLineItem createIndexedConsumerOrderLineItem(String id, int version){

		ConsumerOrderLineItem consumerOrderLineItem = new ConsumerOrderLineItem();
		consumerOrderLineItem.setId(id);
		consumerOrderLineItem.setVersion(version);
		return consumerOrderLineItem;			
		
	}
	
	protected void checkParamsForRemovingConsumerOrderLineItemList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderLineItemIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		for(String consumerOrderLineItemIdItem: consumerOrderLineItemIds){
			checkerOf(userContext).checkIdOfConsumerOrderLineItem(consumerOrderLineItemIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder removeConsumerOrderLineItemList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderLineItemIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingConsumerOrderLineItemList(userContext, consumerOrderId,  consumerOrderLineItemIds, tokensExpr);
			
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
			synchronized(consumerOrder){ 
				//Will be good when the consumerOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				consumerOrderDaoOf(userContext).planToRemoveConsumerOrderLineItemList(consumerOrder, consumerOrderLineItemIds, allTokens());
				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderLineItemList().done());
				deleteRelationListInGraph(userContext, consumerOrder.getConsumerOrderLineItemList());
				return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderLineItemId, int consumerOrderLineItemVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderLineItem(consumerOrderLineItemId);
		checkerOf(userContext).checkVersionOfConsumerOrderLineItem(consumerOrderLineItemVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder removeConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderLineItemId, int consumerOrderLineItemVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingConsumerOrderLineItem(userContext,consumerOrderId, consumerOrderLineItemId, consumerOrderLineItemVersion,tokensExpr);
		
		ConsumerOrderLineItem consumerOrderLineItem = createIndexedConsumerOrderLineItem(consumerOrderLineItemId, consumerOrderLineItemVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.removeConsumerOrderLineItem( consumerOrderLineItem );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderLineItemList().done());
			deleteRelationInGraph(userContext, consumerOrderLineItem);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderLineItemId, int consumerOrderLineItemVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderLineItem(consumerOrderLineItemId);
		checkerOf(userContext).checkVersionOfConsumerOrderLineItem(consumerOrderLineItemVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder copyConsumerOrderLineItemFrom(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderLineItemId, int consumerOrderLineItemVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingConsumerOrderLineItem(userContext,consumerOrderId, consumerOrderLineItemId, consumerOrderLineItemVersion,tokensExpr);
		
		ConsumerOrderLineItem consumerOrderLineItem = createIndexedConsumerOrderLineItem(consumerOrderLineItemId, consumerOrderLineItemVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			consumerOrderLineItem.updateLastUpdateTime(userContext.now());
			
			consumerOrder.copyConsumerOrderLineItemFrom( consumerOrderLineItem );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderLineItemList().done());
			
			userContext.getManagerGroup().getConsumerOrderLineItemManager().onNewInstanceCreated(userContext, (ConsumerOrderLineItem)consumerOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderLineItemId, int consumerOrderLineItemVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderLineItem(consumerOrderLineItemId);
		checkerOf(userContext).checkVersionOfConsumerOrderLineItem(consumerOrderLineItemVersion);
		

		if(ConsumerOrderLineItem.SKU_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkSkuIdOfConsumerOrderLineItem(parseString(newValueExpr));
		}
		
		if(ConsumerOrderLineItem.SKU_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSkuNameOfConsumerOrderLineItem(parseString(newValueExpr));
		}
		
		if(ConsumerOrderLineItem.PRICE_PROPERTY.equals(property)){
			checkerOf(userContext).checkPriceOfConsumerOrderLineItem(parseBigDecimal(newValueExpr));
		}
		
		if(ConsumerOrderLineItem.QUANTITY_PROPERTY.equals(property)){
			checkerOf(userContext).checkQuantityOfConsumerOrderLineItem(parseBigDecimal(newValueExpr));
		}
		
		if(ConsumerOrderLineItem.AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkAmountOfConsumerOrderLineItem(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	
	public  ConsumerOrder updateConsumerOrderLineItem(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderLineItemId, int consumerOrderLineItemVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingConsumerOrderLineItem(userContext, consumerOrderId, consumerOrderLineItemId, consumerOrderLineItemVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withConsumerOrderLineItemList().searchConsumerOrderLineItemListWith(ConsumerOrderLineItem.ID_PROPERTY, "eq", consumerOrderLineItemId).done();
		
		
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, loadTokens);
		
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//consumerOrder.removeConsumerOrderLineItem( consumerOrderLineItem );	
			//make changes to AcceleraterAccount.
			ConsumerOrderLineItem consumerOrderLineItemIndex = createIndexedConsumerOrderLineItem(consumerOrderLineItemId, consumerOrderLineItemVersion);
		
			ConsumerOrderLineItem consumerOrderLineItem = consumerOrder.findTheConsumerOrderLineItem(consumerOrderLineItemIndex);
			if(consumerOrderLineItem == null){
				throw new ConsumerOrderManagerException(consumerOrderLineItem+" is NOT FOUND" );
			}
			
			consumerOrderLineItem.changeProperty(property, newValueExpr);
			consumerOrderLineItem.updateLastUpdateTime(userContext.now());
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderLineItemList().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, String name, BigDecimal amount,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);

		
		checkerOf(userContext).checkNameOfConsumerOrderShippingGroup(name);
		
		checkerOf(userContext).checkAmountOfConsumerOrderShippingGroup(amount);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);

	
	}
	public  ConsumerOrder addConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, String name, BigDecimal amount, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingConsumerOrderShippingGroup(userContext,consumerOrderId,name, amount,tokensExpr);
		
		ConsumerOrderShippingGroup consumerOrderShippingGroup = createConsumerOrderShippingGroup(userContext,name, amount);
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.addConsumerOrderShippingGroup( consumerOrderShippingGroup );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderShippingGroupList().done());
			
			userContext.getManagerGroup().getConsumerOrderShippingGroupManager().onNewInstanceCreated(userContext, consumerOrderShippingGroup);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingConsumerOrderShippingGroupProperties(RetailscmUserContext userContext, String consumerOrderId,String id,String name,BigDecimal amount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderShippingGroup(id);
		
		checkerOf(userContext).checkNameOfConsumerOrderShippingGroup( name);
		checkerOf(userContext).checkAmountOfConsumerOrderShippingGroup( amount);

		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder updateConsumerOrderShippingGroupProperties(RetailscmUserContext userContext, String consumerOrderId, String id,String name,BigDecimal amount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingConsumerOrderShippingGroupProperties(userContext,consumerOrderId,id,name,amount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withConsumerOrderShippingGroupListList()
				.searchConsumerOrderShippingGroupListWith(ConsumerOrderShippingGroup.ID_PROPERTY, "is", id).done();
		
		ConsumerOrder consumerOrderToUpdate = loadConsumerOrder(userContext, consumerOrderId, options);
		
		if(consumerOrderToUpdate.getConsumerOrderShippingGroupList().isEmpty()){
			throw new ConsumerOrderManagerException("ConsumerOrderShippingGroup is NOT FOUND with id: '"+id+"'");
		}
		
		ConsumerOrderShippingGroup item = consumerOrderToUpdate.getConsumerOrderShippingGroupList().first();
		
		item.updateName( name );
		item.updateAmount( amount );

		
		//checkParamsForAddingConsumerOrderShippingGroup(userContext,consumerOrderId,name, code, used,tokensExpr);
		ConsumerOrder consumerOrder = saveConsumerOrder(userContext, consumerOrderToUpdate, tokens().withConsumerOrderShippingGroupList().done());
		synchronized(consumerOrder){ 
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ConsumerOrderShippingGroup createConsumerOrderShippingGroup(RetailscmUserContext userContext, String name, BigDecimal amount) throws Exception{

		ConsumerOrderShippingGroup consumerOrderShippingGroup = new ConsumerOrderShippingGroup();
		
		
		consumerOrderShippingGroup.setName(name);		
		consumerOrderShippingGroup.setAmount(amount);
	
		
		return consumerOrderShippingGroup;
	
		
	}
	
	protected ConsumerOrderShippingGroup createIndexedConsumerOrderShippingGroup(String id, int version){

		ConsumerOrderShippingGroup consumerOrderShippingGroup = new ConsumerOrderShippingGroup();
		consumerOrderShippingGroup.setId(id);
		consumerOrderShippingGroup.setVersion(version);
		return consumerOrderShippingGroup;			
		
	}
	
	protected void checkParamsForRemovingConsumerOrderShippingGroupList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderShippingGroupIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		for(String consumerOrderShippingGroupIdItem: consumerOrderShippingGroupIds){
			checkerOf(userContext).checkIdOfConsumerOrderShippingGroup(consumerOrderShippingGroupIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder removeConsumerOrderShippingGroupList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderShippingGroupIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingConsumerOrderShippingGroupList(userContext, consumerOrderId,  consumerOrderShippingGroupIds, tokensExpr);
			
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
			synchronized(consumerOrder){ 
				//Will be good when the consumerOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				consumerOrderDaoOf(userContext).planToRemoveConsumerOrderShippingGroupList(consumerOrder, consumerOrderShippingGroupIds, allTokens());
				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderShippingGroupList().done());
				deleteRelationListInGraph(userContext, consumerOrder.getConsumerOrderShippingGroupList());
				return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderShippingGroupId, int consumerOrderShippingGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderShippingGroup(consumerOrderShippingGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderShippingGroup(consumerOrderShippingGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder removeConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderShippingGroupId, int consumerOrderShippingGroupVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingConsumerOrderShippingGroup(userContext,consumerOrderId, consumerOrderShippingGroupId, consumerOrderShippingGroupVersion,tokensExpr);
		
		ConsumerOrderShippingGroup consumerOrderShippingGroup = createIndexedConsumerOrderShippingGroup(consumerOrderShippingGroupId, consumerOrderShippingGroupVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.removeConsumerOrderShippingGroup( consumerOrderShippingGroup );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderShippingGroupList().done());
			deleteRelationInGraph(userContext, consumerOrderShippingGroup);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderShippingGroupId, int consumerOrderShippingGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderShippingGroup(consumerOrderShippingGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderShippingGroup(consumerOrderShippingGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder copyConsumerOrderShippingGroupFrom(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderShippingGroupId, int consumerOrderShippingGroupVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingConsumerOrderShippingGroup(userContext,consumerOrderId, consumerOrderShippingGroupId, consumerOrderShippingGroupVersion,tokensExpr);
		
		ConsumerOrderShippingGroup consumerOrderShippingGroup = createIndexedConsumerOrderShippingGroup(consumerOrderShippingGroupId, consumerOrderShippingGroupVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			consumerOrder.copyConsumerOrderShippingGroupFrom( consumerOrderShippingGroup );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderShippingGroupList().done());
			
			userContext.getManagerGroup().getConsumerOrderShippingGroupManager().onNewInstanceCreated(userContext, (ConsumerOrderShippingGroup)consumerOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderShippingGroupId, int consumerOrderShippingGroupVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderShippingGroup(consumerOrderShippingGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderShippingGroup(consumerOrderShippingGroupVersion);
		

		if(ConsumerOrderShippingGroup.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfConsumerOrderShippingGroup(parseString(newValueExpr));
		}
		
		if(ConsumerOrderShippingGroup.AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkAmountOfConsumerOrderShippingGroup(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	
	public  ConsumerOrder updateConsumerOrderShippingGroup(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderShippingGroupId, int consumerOrderShippingGroupVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingConsumerOrderShippingGroup(userContext, consumerOrderId, consumerOrderShippingGroupId, consumerOrderShippingGroupVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withConsumerOrderShippingGroupList().searchConsumerOrderShippingGroupListWith(ConsumerOrderShippingGroup.ID_PROPERTY, "eq", consumerOrderShippingGroupId).done();
		
		
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, loadTokens);
		
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//consumerOrder.removeConsumerOrderShippingGroup( consumerOrderShippingGroup );	
			//make changes to AcceleraterAccount.
			ConsumerOrderShippingGroup consumerOrderShippingGroupIndex = createIndexedConsumerOrderShippingGroup(consumerOrderShippingGroupId, consumerOrderShippingGroupVersion);
		
			ConsumerOrderShippingGroup consumerOrderShippingGroup = consumerOrder.findTheConsumerOrderShippingGroup(consumerOrderShippingGroupIndex);
			if(consumerOrderShippingGroup == null){
				throw new ConsumerOrderManagerException(consumerOrderShippingGroup+" is NOT FOUND" );
			}
			
			consumerOrderShippingGroup.changeProperty(property, newValueExpr);
			
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderShippingGroupList().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, String name, String cardNumber,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);

		
		checkerOf(userContext).checkNameOfConsumerOrderPaymentGroup(name);
		
		checkerOf(userContext).checkCardNumberOfConsumerOrderPaymentGroup(cardNumber);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);

	
	}
	public  ConsumerOrder addConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, String name, String cardNumber, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingConsumerOrderPaymentGroup(userContext,consumerOrderId,name, cardNumber,tokensExpr);
		
		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = createConsumerOrderPaymentGroup(userContext,name, cardNumber);
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.addConsumerOrderPaymentGroup( consumerOrderPaymentGroup );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPaymentGroupList().done());
			
			userContext.getManagerGroup().getConsumerOrderPaymentGroupManager().onNewInstanceCreated(userContext, consumerOrderPaymentGroup);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingConsumerOrderPaymentGroupProperties(RetailscmUserContext userContext, String consumerOrderId,String id,String name,String cardNumber,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(id);
		
		checkerOf(userContext).checkNameOfConsumerOrderPaymentGroup( name);
		checkerOf(userContext).checkCardNumberOfConsumerOrderPaymentGroup( cardNumber);

		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder updateConsumerOrderPaymentGroupProperties(RetailscmUserContext userContext, String consumerOrderId, String id,String name,String cardNumber, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingConsumerOrderPaymentGroupProperties(userContext,consumerOrderId,id,name,cardNumber,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withConsumerOrderPaymentGroupListList()
				.searchConsumerOrderPaymentGroupListWith(ConsumerOrderPaymentGroup.ID_PROPERTY, "is", id).done();
		
		ConsumerOrder consumerOrderToUpdate = loadConsumerOrder(userContext, consumerOrderId, options);
		
		if(consumerOrderToUpdate.getConsumerOrderPaymentGroupList().isEmpty()){
			throw new ConsumerOrderManagerException("ConsumerOrderPaymentGroup is NOT FOUND with id: '"+id+"'");
		}
		
		ConsumerOrderPaymentGroup item = consumerOrderToUpdate.getConsumerOrderPaymentGroupList().first();
		
		item.updateName( name );
		item.updateCardNumber( cardNumber );

		
		//checkParamsForAddingConsumerOrderPaymentGroup(userContext,consumerOrderId,name, code, used,tokensExpr);
		ConsumerOrder consumerOrder = saveConsumerOrder(userContext, consumerOrderToUpdate, tokens().withConsumerOrderPaymentGroupList().done());
		synchronized(consumerOrder){ 
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ConsumerOrderPaymentGroup createConsumerOrderPaymentGroup(RetailscmUserContext userContext, String name, String cardNumber) throws Exception{

		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = new ConsumerOrderPaymentGroup();
		
		
		consumerOrderPaymentGroup.setName(name);		
		consumerOrderPaymentGroup.setCardNumber(cardNumber);
	
		
		return consumerOrderPaymentGroup;
	
		
	}
	
	protected ConsumerOrderPaymentGroup createIndexedConsumerOrderPaymentGroup(String id, int version){

		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = new ConsumerOrderPaymentGroup();
		consumerOrderPaymentGroup.setId(id);
		consumerOrderPaymentGroup.setVersion(version);
		return consumerOrderPaymentGroup;			
		
	}
	
	protected void checkParamsForRemovingConsumerOrderPaymentGroupList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderPaymentGroupIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		for(String consumerOrderPaymentGroupIdItem: consumerOrderPaymentGroupIds){
			checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder removeConsumerOrderPaymentGroupList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderPaymentGroupIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingConsumerOrderPaymentGroupList(userContext, consumerOrderId,  consumerOrderPaymentGroupIds, tokensExpr);
			
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
			synchronized(consumerOrder){ 
				//Will be good when the consumerOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				consumerOrderDaoOf(userContext).planToRemoveConsumerOrderPaymentGroupList(consumerOrder, consumerOrderPaymentGroupIds, allTokens());
				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPaymentGroupList().done());
				deleteRelationListInGraph(userContext, consumerOrder.getConsumerOrderPaymentGroupList());
				return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder removeConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingConsumerOrderPaymentGroup(userContext,consumerOrderId, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion,tokensExpr);
		
		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = createIndexedConsumerOrderPaymentGroup(consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.removeConsumerOrderPaymentGroup( consumerOrderPaymentGroup );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPaymentGroupList().done());
			deleteRelationInGraph(userContext, consumerOrderPaymentGroup);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder copyConsumerOrderPaymentGroupFrom(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingConsumerOrderPaymentGroup(userContext,consumerOrderId, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion,tokensExpr);
		
		ConsumerOrderPaymentGroup consumerOrderPaymentGroup = createIndexedConsumerOrderPaymentGroup(consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			consumerOrder.copyConsumerOrderPaymentGroupFrom( consumerOrderPaymentGroup );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPaymentGroupList().done());
			
			userContext.getManagerGroup().getConsumerOrderPaymentGroupManager().onNewInstanceCreated(userContext, (ConsumerOrderPaymentGroup)consumerOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupId);
		checkerOf(userContext).checkVersionOfConsumerOrderPaymentGroup(consumerOrderPaymentGroupVersion);
		

		if(ConsumerOrderPaymentGroup.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfConsumerOrderPaymentGroup(parseString(newValueExpr));
		}
		
		if(ConsumerOrderPaymentGroup.CARD_NUMBER_PROPERTY.equals(property)){
			checkerOf(userContext).checkCardNumberOfConsumerOrderPaymentGroup(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	
	public  ConsumerOrder updateConsumerOrderPaymentGroup(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderPaymentGroupId, int consumerOrderPaymentGroupVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingConsumerOrderPaymentGroup(userContext, consumerOrderId, consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withConsumerOrderPaymentGroupList().searchConsumerOrderPaymentGroupListWith(ConsumerOrderPaymentGroup.ID_PROPERTY, "eq", consumerOrderPaymentGroupId).done();
		
		
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, loadTokens);
		
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//consumerOrder.removeConsumerOrderPaymentGroup( consumerOrderPaymentGroup );	
			//make changes to AcceleraterAccount.
			ConsumerOrderPaymentGroup consumerOrderPaymentGroupIndex = createIndexedConsumerOrderPaymentGroup(consumerOrderPaymentGroupId, consumerOrderPaymentGroupVersion);
		
			ConsumerOrderPaymentGroup consumerOrderPaymentGroup = consumerOrder.findTheConsumerOrderPaymentGroup(consumerOrderPaymentGroupIndex);
			if(consumerOrderPaymentGroup == null){
				throw new ConsumerOrderManagerException(consumerOrderPaymentGroup+" is NOT FOUND" );
			}
			
			consumerOrderPaymentGroup.changeProperty(property, newValueExpr);
			
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPaymentGroupList().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, String name, BigDecimal amount, String provider,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);

		
		checkerOf(userContext).checkNameOfConsumerOrderPriceAdjustment(name);
		
		checkerOf(userContext).checkAmountOfConsumerOrderPriceAdjustment(amount);
		
		checkerOf(userContext).checkProviderOfConsumerOrderPriceAdjustment(provider);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);

	
	}
	public  ConsumerOrder addConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, String name, BigDecimal amount, String provider, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingConsumerOrderPriceAdjustment(userContext,consumerOrderId,name, amount, provider,tokensExpr);
		
		ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment = createConsumerOrderPriceAdjustment(userContext,name, amount, provider);
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.addConsumerOrderPriceAdjustment( consumerOrderPriceAdjustment );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPriceAdjustmentList().done());
			
			userContext.getManagerGroup().getConsumerOrderPriceAdjustmentManager().onNewInstanceCreated(userContext, consumerOrderPriceAdjustment);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingConsumerOrderPriceAdjustmentProperties(RetailscmUserContext userContext, String consumerOrderId,String id,String name,BigDecimal amount,String provider,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPriceAdjustment(id);
		
		checkerOf(userContext).checkNameOfConsumerOrderPriceAdjustment( name);
		checkerOf(userContext).checkAmountOfConsumerOrderPriceAdjustment( amount);
		checkerOf(userContext).checkProviderOfConsumerOrderPriceAdjustment( provider);

		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder updateConsumerOrderPriceAdjustmentProperties(RetailscmUserContext userContext, String consumerOrderId, String id,String name,BigDecimal amount,String provider, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingConsumerOrderPriceAdjustmentProperties(userContext,consumerOrderId,id,name,amount,provider,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withConsumerOrderPriceAdjustmentListList()
				.searchConsumerOrderPriceAdjustmentListWith(ConsumerOrderPriceAdjustment.ID_PROPERTY, "is", id).done();
		
		ConsumerOrder consumerOrderToUpdate = loadConsumerOrder(userContext, consumerOrderId, options);
		
		if(consumerOrderToUpdate.getConsumerOrderPriceAdjustmentList().isEmpty()){
			throw new ConsumerOrderManagerException("ConsumerOrderPriceAdjustment is NOT FOUND with id: '"+id+"'");
		}
		
		ConsumerOrderPriceAdjustment item = consumerOrderToUpdate.getConsumerOrderPriceAdjustmentList().first();
		
		item.updateName( name );
		item.updateAmount( amount );
		item.updateProvider( provider );

		
		//checkParamsForAddingConsumerOrderPriceAdjustment(userContext,consumerOrderId,name, code, used,tokensExpr);
		ConsumerOrder consumerOrder = saveConsumerOrder(userContext, consumerOrderToUpdate, tokens().withConsumerOrderPriceAdjustmentList().done());
		synchronized(consumerOrder){ 
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ConsumerOrderPriceAdjustment createConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String name, BigDecimal amount, String provider) throws Exception{

		ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment = new ConsumerOrderPriceAdjustment();
		
		
		consumerOrderPriceAdjustment.setName(name);		
		consumerOrderPriceAdjustment.setAmount(amount);		
		consumerOrderPriceAdjustment.setProvider(provider);
	
		
		return consumerOrderPriceAdjustment;
	
		
	}
	
	protected ConsumerOrderPriceAdjustment createIndexedConsumerOrderPriceAdjustment(String id, int version){

		ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment = new ConsumerOrderPriceAdjustment();
		consumerOrderPriceAdjustment.setId(id);
		consumerOrderPriceAdjustment.setVersion(version);
		return consumerOrderPriceAdjustment;			
		
	}
	
	protected void checkParamsForRemovingConsumerOrderPriceAdjustmentList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderPriceAdjustmentIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		for(String consumerOrderPriceAdjustmentIdItem: consumerOrderPriceAdjustmentIds){
			checkerOf(userContext).checkIdOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder removeConsumerOrderPriceAdjustmentList(RetailscmUserContext userContext, String consumerOrderId, 
			String consumerOrderPriceAdjustmentIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingConsumerOrderPriceAdjustmentList(userContext, consumerOrderId,  consumerOrderPriceAdjustmentIds, tokensExpr);
			
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
			synchronized(consumerOrder){ 
				//Will be good when the consumerOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				consumerOrderDaoOf(userContext).planToRemoveConsumerOrderPriceAdjustmentList(consumerOrder, consumerOrderPriceAdjustmentIds, allTokens());
				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPriceAdjustmentList().done());
				deleteRelationListInGraph(userContext, consumerOrder.getConsumerOrderPriceAdjustmentList());
				return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentId);
		checkerOf(userContext).checkVersionOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder removeConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingConsumerOrderPriceAdjustment(userContext,consumerOrderId, consumerOrderPriceAdjustmentId, consumerOrderPriceAdjustmentVersion,tokensExpr);
		
		ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment = createIndexedConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentId, consumerOrderPriceAdjustmentVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.removeConsumerOrderPriceAdjustment( consumerOrderPriceAdjustment );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPriceAdjustmentList().done());
			deleteRelationInGraph(userContext, consumerOrderPriceAdjustment);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentId);
		checkerOf(userContext).checkVersionOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder copyConsumerOrderPriceAdjustmentFrom(RetailscmUserContext userContext, String consumerOrderId, 
		String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingConsumerOrderPriceAdjustment(userContext,consumerOrderId, consumerOrderPriceAdjustmentId, consumerOrderPriceAdjustmentVersion,tokensExpr);
		
		ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment = createIndexedConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentId, consumerOrderPriceAdjustmentVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			consumerOrder.copyConsumerOrderPriceAdjustmentFrom( consumerOrderPriceAdjustment );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPriceAdjustmentList().done());
			
			userContext.getManagerGroup().getConsumerOrderPriceAdjustmentManager().onNewInstanceCreated(userContext, (ConsumerOrderPriceAdjustment)consumerOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentId);
		checkerOf(userContext).checkVersionOfConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentVersion);
		

		if(ConsumerOrderPriceAdjustment.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfConsumerOrderPriceAdjustment(parseString(newValueExpr));
		}
		
		if(ConsumerOrderPriceAdjustment.AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkAmountOfConsumerOrderPriceAdjustment(parseBigDecimal(newValueExpr));
		}
		
		if(ConsumerOrderPriceAdjustment.PROVIDER_PROPERTY.equals(property)){
			checkerOf(userContext).checkProviderOfConsumerOrderPriceAdjustment(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	
	public  ConsumerOrder updateConsumerOrderPriceAdjustment(RetailscmUserContext userContext, String consumerOrderId, String consumerOrderPriceAdjustmentId, int consumerOrderPriceAdjustmentVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingConsumerOrderPriceAdjustment(userContext, consumerOrderId, consumerOrderPriceAdjustmentId, consumerOrderPriceAdjustmentVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withConsumerOrderPriceAdjustmentList().searchConsumerOrderPriceAdjustmentListWith(ConsumerOrderPriceAdjustment.ID_PROPERTY, "eq", consumerOrderPriceAdjustmentId).done();
		
		
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, loadTokens);
		
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//consumerOrder.removeConsumerOrderPriceAdjustment( consumerOrderPriceAdjustment );	
			//make changes to AcceleraterAccount.
			ConsumerOrderPriceAdjustment consumerOrderPriceAdjustmentIndex = createIndexedConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentId, consumerOrderPriceAdjustmentVersion);
		
			ConsumerOrderPriceAdjustment consumerOrderPriceAdjustment = consumerOrder.findTheConsumerOrderPriceAdjustment(consumerOrderPriceAdjustmentIndex);
			if(consumerOrderPriceAdjustment == null){
				throw new ConsumerOrderManagerException(consumerOrderPriceAdjustment+" is NOT FOUND" );
			}
			
			consumerOrderPriceAdjustment.changeProperty(property, newValueExpr);
			
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withConsumerOrderPriceAdjustmentList().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, Date occureTime, String ownerId, String number, BigDecimal amount,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);

		
		checkerOf(userContext).checkOccureTimeOfRetailStoreMemberGiftCardConsumeRecord(occureTime);
		
		checkerOf(userContext).checkOwnerIdOfRetailStoreMemberGiftCardConsumeRecord(ownerId);
		
		checkerOf(userContext).checkNumberOfRetailStoreMemberGiftCardConsumeRecord(number);
		
		checkerOf(userContext).checkAmountOfRetailStoreMemberGiftCardConsumeRecord(amount);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);

	
	}
	public  ConsumerOrder addRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, Date occureTime, String ownerId, String number, BigDecimal amount, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingRetailStoreMemberGiftCardConsumeRecord(userContext,consumerOrderId,occureTime, ownerId, number, amount,tokensExpr);
		
		RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecord = createRetailStoreMemberGiftCardConsumeRecord(userContext,occureTime, ownerId, number, amount);
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.addRetailStoreMemberGiftCardConsumeRecord( retailStoreMemberGiftCardConsumeRecord );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
			
			userContext.getManagerGroup().getRetailStoreMemberGiftCardConsumeRecordManager().onNewInstanceCreated(userContext, retailStoreMemberGiftCardConsumeRecord);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingRetailStoreMemberGiftCardConsumeRecordProperties(RetailscmUserContext userContext, String consumerOrderId,String id,Date occureTime,String number,BigDecimal amount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfRetailStoreMemberGiftCardConsumeRecord(id);
		
		checkerOf(userContext).checkOccureTimeOfRetailStoreMemberGiftCardConsumeRecord( occureTime);
		checkerOf(userContext).checkNumberOfRetailStoreMemberGiftCardConsumeRecord( number);
		checkerOf(userContext).checkAmountOfRetailStoreMemberGiftCardConsumeRecord( amount);

		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder updateRetailStoreMemberGiftCardConsumeRecordProperties(RetailscmUserContext userContext, String consumerOrderId, String id,Date occureTime,String number,BigDecimal amount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingRetailStoreMemberGiftCardConsumeRecordProperties(userContext,consumerOrderId,id,occureTime,number,amount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withRetailStoreMemberGiftCardConsumeRecordListList()
				.searchRetailStoreMemberGiftCardConsumeRecordListWith(RetailStoreMemberGiftCardConsumeRecord.ID_PROPERTY, "is", id).done();
		
		ConsumerOrder consumerOrderToUpdate = loadConsumerOrder(userContext, consumerOrderId, options);
		
		if(consumerOrderToUpdate.getRetailStoreMemberGiftCardConsumeRecordList().isEmpty()){
			throw new ConsumerOrderManagerException("RetailStoreMemberGiftCardConsumeRecord is NOT FOUND with id: '"+id+"'");
		}
		
		RetailStoreMemberGiftCardConsumeRecord item = consumerOrderToUpdate.getRetailStoreMemberGiftCardConsumeRecordList().first();
		
		item.updateOccureTime( occureTime );
		item.updateNumber( number );
		item.updateAmount( amount );

		
		//checkParamsForAddingRetailStoreMemberGiftCardConsumeRecord(userContext,consumerOrderId,name, code, used,tokensExpr);
		ConsumerOrder consumerOrder = saveConsumerOrder(userContext, consumerOrderToUpdate, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
		synchronized(consumerOrder){ 
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected RetailStoreMemberGiftCardConsumeRecord createRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, Date occureTime, String ownerId, String number, BigDecimal amount) throws Exception{

		RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecord = new RetailStoreMemberGiftCardConsumeRecord();
		
		
		retailStoreMemberGiftCardConsumeRecord.setOccureTime(occureTime);		
		RetailStoreMemberGiftCard  owner = new RetailStoreMemberGiftCard();
		owner.setId(ownerId);		
		retailStoreMemberGiftCardConsumeRecord.setOwner(owner);		
		retailStoreMemberGiftCardConsumeRecord.setNumber(number);		
		retailStoreMemberGiftCardConsumeRecord.setAmount(amount);
	
		
		return retailStoreMemberGiftCardConsumeRecord;
	
		
	}
	
	protected RetailStoreMemberGiftCardConsumeRecord createIndexedRetailStoreMemberGiftCardConsumeRecord(String id, int version){

		RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecord = new RetailStoreMemberGiftCardConsumeRecord();
		retailStoreMemberGiftCardConsumeRecord.setId(id);
		retailStoreMemberGiftCardConsumeRecord.setVersion(version);
		return retailStoreMemberGiftCardConsumeRecord;			
		
	}
	
	protected void checkParamsForRemovingRetailStoreMemberGiftCardConsumeRecordList(RetailscmUserContext userContext, String consumerOrderId, 
			String retailStoreMemberGiftCardConsumeRecordIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		for(String retailStoreMemberGiftCardConsumeRecordIdItem: retailStoreMemberGiftCardConsumeRecordIds){
			checkerOf(userContext).checkIdOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
		
	}
	public  ConsumerOrder removeRetailStoreMemberGiftCardConsumeRecordList(RetailscmUserContext userContext, String consumerOrderId, 
			String retailStoreMemberGiftCardConsumeRecordIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingRetailStoreMemberGiftCardConsumeRecordList(userContext, consumerOrderId,  retailStoreMemberGiftCardConsumeRecordIds, tokensExpr);
			
			
			ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
			synchronized(consumerOrder){ 
				//Will be good when the consumerOrder loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				consumerOrderDaoOf(userContext).planToRemoveRetailStoreMemberGiftCardConsumeRecordList(consumerOrder, retailStoreMemberGiftCardConsumeRecordIds, allTokens());
				consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
				deleteRelationListInGraph(userContext, consumerOrder.getRetailStoreMemberGiftCardConsumeRecordList());
				return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, 
		String retailStoreMemberGiftCardConsumeRecordId, int retailStoreMemberGiftCardConsumeRecordVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordId);
		checkerOf(userContext).checkVersionOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder removeRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, 
		String retailStoreMemberGiftCardConsumeRecordId, int retailStoreMemberGiftCardConsumeRecordVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingRetailStoreMemberGiftCardConsumeRecord(userContext,consumerOrderId, retailStoreMemberGiftCardConsumeRecordId, retailStoreMemberGiftCardConsumeRecordVersion,tokensExpr);
		
		RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecord = createIndexedRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordId, retailStoreMemberGiftCardConsumeRecordVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			consumerOrder.removeRetailStoreMemberGiftCardConsumeRecord( retailStoreMemberGiftCardConsumeRecord );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
			deleteRelationInGraph(userContext, retailStoreMemberGiftCardConsumeRecord);
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, 
		String retailStoreMemberGiftCardConsumeRecordId, int retailStoreMemberGiftCardConsumeRecordVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfConsumerOrder( consumerOrderId);
		checkerOf(userContext).checkIdOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordId);
		checkerOf(userContext).checkVersionOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	public  ConsumerOrder copyRetailStoreMemberGiftCardConsumeRecordFrom(RetailscmUserContext userContext, String consumerOrderId, 
		String retailStoreMemberGiftCardConsumeRecordId, int retailStoreMemberGiftCardConsumeRecordVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingRetailStoreMemberGiftCardConsumeRecord(userContext,consumerOrderId, retailStoreMemberGiftCardConsumeRecordId, retailStoreMemberGiftCardConsumeRecordVersion,tokensExpr);
		
		RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecord = createIndexedRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordId, retailStoreMemberGiftCardConsumeRecordVersion);
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, allTokens());
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			consumerOrder.copyRetailStoreMemberGiftCardConsumeRecordFrom( retailStoreMemberGiftCardConsumeRecord );		
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
			
			userContext.getManagerGroup().getRetailStoreMemberGiftCardConsumeRecordManager().onNewInstanceCreated(userContext, (RetailStoreMemberGiftCardConsumeRecord)consumerOrder.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, String retailStoreMemberGiftCardConsumeRecordId, int retailStoreMemberGiftCardConsumeRecordVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfConsumerOrder(consumerOrderId);
		checkerOf(userContext).checkIdOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordId);
		checkerOf(userContext).checkVersionOfRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordVersion);
		

		if(RetailStoreMemberGiftCardConsumeRecord.OCCURE_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkOccureTimeOfRetailStoreMemberGiftCardConsumeRecord(parseDate(newValueExpr));
		}
		
		if(RetailStoreMemberGiftCardConsumeRecord.NUMBER_PROPERTY.equals(property)){
			checkerOf(userContext).checkNumberOfRetailStoreMemberGiftCardConsumeRecord(parseString(newValueExpr));
		}
		
		if(RetailStoreMemberGiftCardConsumeRecord.AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkAmountOfRetailStoreMemberGiftCardConsumeRecord(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ConsumerOrderManagerException.class);
	
	}
	
	public  ConsumerOrder updateRetailStoreMemberGiftCardConsumeRecord(RetailscmUserContext userContext, String consumerOrderId, String retailStoreMemberGiftCardConsumeRecordId, int retailStoreMemberGiftCardConsumeRecordVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingRetailStoreMemberGiftCardConsumeRecord(userContext, consumerOrderId, retailStoreMemberGiftCardConsumeRecordId, retailStoreMemberGiftCardConsumeRecordVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withRetailStoreMemberGiftCardConsumeRecordList().searchRetailStoreMemberGiftCardConsumeRecordListWith(RetailStoreMemberGiftCardConsumeRecord.ID_PROPERTY, "eq", retailStoreMemberGiftCardConsumeRecordId).done();
		
		
		
		ConsumerOrder consumerOrder = loadConsumerOrder(userContext, consumerOrderId, loadTokens);
		
		synchronized(consumerOrder){ 
			//Will be good when the consumerOrder loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//consumerOrder.removeRetailStoreMemberGiftCardConsumeRecord( retailStoreMemberGiftCardConsumeRecord );	
			//make changes to AcceleraterAccount.
			RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecordIndex = createIndexedRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordId, retailStoreMemberGiftCardConsumeRecordVersion);
		
			RetailStoreMemberGiftCardConsumeRecord retailStoreMemberGiftCardConsumeRecord = consumerOrder.findTheRetailStoreMemberGiftCardConsumeRecord(retailStoreMemberGiftCardConsumeRecordIndex);
			if(retailStoreMemberGiftCardConsumeRecord == null){
				throw new ConsumerOrderManagerException(retailStoreMemberGiftCardConsumeRecord+" is NOT FOUND" );
			}
			
			retailStoreMemberGiftCardConsumeRecord.changeProperty(property, newValueExpr);
			
			consumerOrder = saveConsumerOrder(userContext, consumerOrder, tokens().withRetailStoreMemberGiftCardConsumeRecordList().done());
			return present(userContext,consumerOrder, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(RetailscmUserContext userContext, ConsumerOrder newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


