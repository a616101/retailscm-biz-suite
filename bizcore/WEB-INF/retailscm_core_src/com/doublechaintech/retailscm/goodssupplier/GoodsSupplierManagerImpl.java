
package com.doublechaintech.retailscm.goodssupplier;

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

import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.supplyorder.SupplyOrder;
import com.doublechaintech.retailscm.supplierproduct.SupplierProduct;
import com.doublechaintech.retailscm.accountset.AccountSet;

import com.doublechaintech.retailscm.retailstorecountrycenter.CandidateRetailStoreCountryCenter;

import com.doublechaintech.retailscm.supplyorderapproval.SupplyOrderApproval;
import com.doublechaintech.retailscm.goodssupplier.GoodsSupplier;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.supplyorderdelivery.SupplyOrderDelivery;
import com.doublechaintech.retailscm.retailstore.RetailStore;
import com.doublechaintech.retailscm.supplyorderpicking.SupplyOrderPicking;
import com.doublechaintech.retailscm.supplyorderprocessing.SupplyOrderProcessing;
import com.doublechaintech.retailscm.supplyorderconfirmation.SupplyOrderConfirmation;
import com.doublechaintech.retailscm.supplyordershipment.SupplyOrderShipment;






public class GoodsSupplierManagerImpl extends CustomRetailscmCheckerManager implements GoodsSupplierManager {
	
	private static final String SERVICE_TYPE = "GoodsSupplier";
	@Override
	public GoodsSupplierDAO daoOf(RetailscmUserContext userContext) {
		return goodsSupplierDaoOf(userContext);
	}
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws GoodsSupplierManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new GoodsSupplierManagerException(message);

	}
	
	

 	protected GoodsSupplier saveGoodsSupplier(RetailscmUserContext userContext, GoodsSupplier goodsSupplier, String [] tokensExpr) throws Exception{	
 		//return getGoodsSupplierDAO().save(goodsSupplier, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveGoodsSupplier(userContext, goodsSupplier, tokens);
 	}
 	
 	protected GoodsSupplier saveGoodsSupplierDetail(RetailscmUserContext userContext, GoodsSupplier goodsSupplier) throws Exception{	

 		
 		return saveGoodsSupplier(userContext, goodsSupplier, allTokens());
 	}
 	
 	public GoodsSupplier loadGoodsSupplier(RetailscmUserContext userContext, String goodsSupplierId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).throwExceptionIfHasErrors( GoodsSupplierManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		GoodsSupplier goodsSupplier = loadGoodsSupplier( userContext, goodsSupplierId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,goodsSupplier, tokens);
 	}
 	
 	
 	 public GoodsSupplier searchGoodsSupplier(RetailscmUserContext userContext, String goodsSupplierId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).throwExceptionIfHasErrors( GoodsSupplierManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		GoodsSupplier goodsSupplier = loadGoodsSupplier( userContext, goodsSupplierId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,goodsSupplier, tokens);
 	}
 	
 	

 	protected GoodsSupplier present(RetailscmUserContext userContext, GoodsSupplier goodsSupplier, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,goodsSupplier,tokens);
		
		
		GoodsSupplier  goodsSupplierToPresent = goodsSupplierDaoOf(userContext).present(goodsSupplier, tokens);
		
		List<BaseEntity> entityListToNaming = goodsSupplierToPresent.collectRefercencesFromLists();
		goodsSupplierDaoOf(userContext).alias(entityListToNaming);
		
		return  goodsSupplierToPresent;
		
		
	}
 
 	
 	
 	public GoodsSupplier loadGoodsSupplierDetail(RetailscmUserContext userContext, String goodsSupplierId) throws Exception{	
 		GoodsSupplier goodsSupplier = loadGoodsSupplier( userContext, goodsSupplierId, allTokens());
 		return present(userContext,goodsSupplier, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String goodsSupplierId) throws Exception{	
 		GoodsSupplier goodsSupplier = loadGoodsSupplier( userContext, goodsSupplierId, viewTokens());
 		return present(userContext,goodsSupplier, allTokens());
		
 	}
 	protected GoodsSupplier saveGoodsSupplier(RetailscmUserContext userContext, GoodsSupplier goodsSupplier, Map<String,Object>tokens) throws Exception{	
 		return goodsSupplierDaoOf(userContext).save(goodsSupplier, tokens);
 	}
 	protected GoodsSupplier loadGoodsSupplier(RetailscmUserContext userContext, String goodsSupplierId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).throwExceptionIfHasErrors( GoodsSupplierManagerException.class);

 
 		return goodsSupplierDaoOf(userContext).load(goodsSupplierId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, GoodsSupplier goodsSupplier, Map<String, Object> tokens){
		super.addActions(userContext, goodsSupplier, tokens);
		
		addAction(userContext, goodsSupplier, tokens,"@create","createGoodsSupplier","createGoodsSupplier/","main","primary");
		addAction(userContext, goodsSupplier, tokens,"@update","updateGoodsSupplier","updateGoodsSupplier/"+goodsSupplier.getId()+"/","main","primary");
		addAction(userContext, goodsSupplier, tokens,"@copy","cloneGoodsSupplier","cloneGoodsSupplier/"+goodsSupplier.getId()+"/","main","primary");
		
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.transfer_to_belong_to","transferToAnotherBelongTo","transferToAnotherBelongTo/"+goodsSupplier.getId()+"/","main","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.addSupplierProduct","addSupplierProduct","addSupplierProduct/"+goodsSupplier.getId()+"/","supplierProductList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.removeSupplierProduct","removeSupplierProduct","removeSupplierProduct/"+goodsSupplier.getId()+"/","supplierProductList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.updateSupplierProduct","updateSupplierProduct","updateSupplierProduct/"+goodsSupplier.getId()+"/","supplierProductList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.copySupplierProductFrom","copySupplierProductFrom","copySupplierProductFrom/"+goodsSupplier.getId()+"/","supplierProductList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.addSupplyOrder","addSupplyOrder","addSupplyOrder/"+goodsSupplier.getId()+"/","supplyOrderList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.removeSupplyOrder","removeSupplyOrder","removeSupplyOrder/"+goodsSupplier.getId()+"/","supplyOrderList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.updateSupplyOrder","updateSupplyOrder","updateSupplyOrder/"+goodsSupplier.getId()+"/","supplyOrderList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.copySupplyOrderFrom","copySupplyOrderFrom","copySupplyOrderFrom/"+goodsSupplier.getId()+"/","supplyOrderList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.addAccountSet","addAccountSet","addAccountSet/"+goodsSupplier.getId()+"/","accountSetList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.removeAccountSet","removeAccountSet","removeAccountSet/"+goodsSupplier.getId()+"/","accountSetList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.updateAccountSet","updateAccountSet","updateAccountSet/"+goodsSupplier.getId()+"/","accountSetList","primary");
		addAction(userContext, goodsSupplier, tokens,"goods_supplier.copyAccountSetFrom","copyAccountSetFrom","copyAccountSetFrom/"+goodsSupplier.getId()+"/","accountSetList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, GoodsSupplier goodsSupplier, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public GoodsSupplier createGoodsSupplier(RetailscmUserContext userContext, String name,String supplyProduct,String belongToId,String contactNumber,String description) throws Exception
	//public GoodsSupplier createGoodsSupplier(RetailscmUserContext userContext,String name, String supplyProduct, String belongToId, String contactNumber, String description) throws Exception
	{
		
		

		

		checkerOf(userContext).checkNameOfGoodsSupplier(name);
		checkerOf(userContext).checkSupplyProductOfGoodsSupplier(supplyProduct);
		checkerOf(userContext).checkContactNumberOfGoodsSupplier(contactNumber);
		checkerOf(userContext).checkDescriptionOfGoodsSupplier(description);
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);


		GoodsSupplier goodsSupplier=createNewGoodsSupplier();	

		goodsSupplier.setName(name);
		goodsSupplier.setSupplyProduct(supplyProduct);
			
		RetailStoreCountryCenter belongTo = loadRetailStoreCountryCenter(userContext, belongToId,emptyOptions());
		goodsSupplier.setBelongTo(belongTo);
		
		
		goodsSupplier.setContactNumber(contactNumber);
		goodsSupplier.setDescription(description);
		goodsSupplier.setLastUpdateTime(userContext.now());

		goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, emptyOptions());
		
		onNewInstanceCreated(userContext, goodsSupplier);
		return goodsSupplier;

		
	}
	protected GoodsSupplier createNewGoodsSupplier() 
	{
		
		return new GoodsSupplier();		
	}
	
	protected void checkParamsForUpdatingGoodsSupplier(RetailscmUserContext userContext,String goodsSupplierId, int goodsSupplierVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkVersionOfGoodsSupplier( goodsSupplierVersion);
		

		if(GoodsSupplier.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfGoodsSupplier(parseString(newValueExpr));
		}
		if(GoodsSupplier.SUPPLY_PRODUCT_PROPERTY.equals(property)){
			checkerOf(userContext).checkSupplyProductOfGoodsSupplier(parseString(newValueExpr));
		}		

		
		if(GoodsSupplier.CONTACT_NUMBER_PROPERTY.equals(property)){
			checkerOf(userContext).checkContactNumberOfGoodsSupplier(parseString(newValueExpr));
		}
		if(GoodsSupplier.DESCRIPTION_PROPERTY.equals(property)){
			checkerOf(userContext).checkDescriptionOfGoodsSupplier(parseString(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
		
	}
	
	
	
	public GoodsSupplier clone(RetailscmUserContext userContext, String fromGoodsSupplierId) throws Exception{
		
		return goodsSupplierDaoOf(userContext).clone(fromGoodsSupplierId, this.allTokens());
	}
	
	public GoodsSupplier internalSaveGoodsSupplier(RetailscmUserContext userContext, GoodsSupplier goodsSupplier) throws Exception 
	{
		return internalSaveGoodsSupplier(userContext, goodsSupplier, allTokens());

	}
	public GoodsSupplier internalSaveGoodsSupplier(RetailscmUserContext userContext, GoodsSupplier goodsSupplier, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingGoodsSupplier(userContext, goodsSupplierId, goodsSupplierVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(goodsSupplier){ 
			//will be good when the goodsSupplier loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to GoodsSupplier.
			if (goodsSupplier.isChanged()){
			goodsSupplier.updateLastUpdateTime(userContext.now());
			}
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, options);
			return goodsSupplier;
			
		}

	}
	
	public GoodsSupplier updateGoodsSupplier(RetailscmUserContext userContext,String goodsSupplierId, int goodsSupplierVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingGoodsSupplier(userContext, goodsSupplierId, goodsSupplierVersion, property, newValueExpr, tokensExpr);
		
		
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		if(goodsSupplier.getVersion() != goodsSupplierVersion){
			String message = "The target version("+goodsSupplier.getVersion()+") is not equals to version("+goodsSupplierVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(goodsSupplier){ 
			//will be good when the goodsSupplier loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to GoodsSupplier.
			goodsSupplier.updateLastUpdateTime(userContext.now());
			goodsSupplier.changeProperty(property, newValueExpr);
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().done());
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
			//return saveGoodsSupplier(userContext, goodsSupplier, tokens().done());
		}

	}
	
	public GoodsSupplier updateGoodsSupplierProperty(RetailscmUserContext userContext,String goodsSupplierId, int goodsSupplierVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingGoodsSupplier(userContext, goodsSupplierId, goodsSupplierVersion, property, newValueExpr, tokensExpr);
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		if(goodsSupplier.getVersion() != goodsSupplierVersion){
			String message = "The target version("+goodsSupplier.getVersion()+") is not equals to version("+goodsSupplierVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(goodsSupplier){ 
			//will be good when the goodsSupplier loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to GoodsSupplier.
			
			goodsSupplier.changeProperty(property, newValueExpr);
			goodsSupplier.updateLastUpdateTime(userContext.now());
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().done());
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
			//return saveGoodsSupplier(userContext, goodsSupplier, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected GoodsSupplierTokens tokens(){
		return GoodsSupplierTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return GoodsSupplierTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortSupplierProductListWith("id","desc")
		.sortSupplyOrderListWith("id","desc")
		.sortAccountSetListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return GoodsSupplierTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherBelongTo(RetailscmUserContext userContext, String goodsSupplierId, String anotherBelongToId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
 		checkerOf(userContext).checkIdOfRetailStoreCountryCenter(anotherBelongToId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
 		
 	}
 	public GoodsSupplier transferToAnotherBelongTo(RetailscmUserContext userContext, String goodsSupplierId, String anotherBelongToId) throws Exception
 	{
 		checkParamsForTransferingAnotherBelongTo(userContext, goodsSupplierId,anotherBelongToId);
 
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());	
		synchronized(goodsSupplier){
			//will be good when the goodsSupplier loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreCountryCenter belongTo = loadRetailStoreCountryCenter(userContext, anotherBelongToId, emptyOptions());		
			goodsSupplier.updateBelongTo(belongTo);		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, emptyOptions());
			
			return present(userContext,goodsSupplier, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateRetailStoreCountryCenter requestCandidateBelongTo(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateRetailStoreCountryCenter result = new CandidateRetailStoreCountryCenter();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<RetailStoreCountryCenter> candidateList = retailStoreCountryCenterDaoOf(userContext).requestCandidateRetailStoreCountryCenterForGoodsSupplier(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected RetailStoreCountryCenter loadRetailStoreCountryCenter(RetailscmUserContext userContext, String newBelongToId, Map<String,Object> options) throws Exception
 	{
		
 		return retailStoreCountryCenterDaoOf(userContext).load(newBelongToId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String goodsSupplierId, int goodsSupplierVersion) throws Exception {
		//deleteInternal(userContext, goodsSupplierId, goodsSupplierVersion);		
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String goodsSupplierId, int goodsSupplierVersion) throws Exception{
			
		goodsSupplierDaoOf(userContext).delete(goodsSupplierId, goodsSupplierVersion);
	}
	
	public GoodsSupplier forgetByAll(RetailscmUserContext userContext, String goodsSupplierId, int goodsSupplierVersion) throws Exception {
		return forgetByAllInternal(userContext, goodsSupplierId, goodsSupplierVersion);		
	}
	protected GoodsSupplier forgetByAllInternal(RetailscmUserContext userContext,
			String goodsSupplierId, int goodsSupplierVersion) throws Exception{
			
		return goodsSupplierDaoOf(userContext).disconnectFromAll(goodsSupplierId, goodsSupplierVersion);
	}
	
	

	
	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new GoodsSupplierManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return goodsSupplierDaoOf(userContext).deleteAll();
	}


	//disconnect GoodsSupplier with buyer in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByBuyer(RetailscmUserContext userContext, String goodsSupplierId, String buyerId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithBuyer(goodsSupplier, buyerId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with confirmation in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByConfirmation(RetailscmUserContext userContext, String goodsSupplierId, String confirmationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithConfirmation(goodsSupplier, confirmationId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with approval in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByApproval(RetailscmUserContext userContext, String goodsSupplierId, String approvalId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithApproval(goodsSupplier, approvalId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with processing in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByProcessing(RetailscmUserContext userContext, String goodsSupplierId, String processingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithProcessing(goodsSupplier, processingId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with picking in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByPicking(RetailscmUserContext userContext, String goodsSupplierId, String pickingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithPicking(goodsSupplier, pickingId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with shipment in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByShipment(RetailscmUserContext userContext, String goodsSupplierId, String shipmentId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithShipment(goodsSupplier, shipmentId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with delivery in SupplyOrder
	protected GoodsSupplier breakWithSupplyOrderByDelivery(RetailscmUserContext userContext, String goodsSupplierId, String deliveryId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderListWithDelivery(goodsSupplier, deliveryId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with country_center in AccountSet
	protected GoodsSupplier breakWithAccountSetByCountryCenter(RetailscmUserContext userContext, String goodsSupplierId, String countryCenterId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveAccountSetListWithCountryCenter(goodsSupplier, countryCenterId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
				return goodsSupplier;
			}
	}
	//disconnect GoodsSupplier with retail_store in AccountSet
	protected GoodsSupplier breakWithAccountSetByRetailStore(RetailscmUserContext userContext, String goodsSupplierId, String retailStoreId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());

			synchronized(goodsSupplier){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				goodsSupplierDaoOf(userContext).planToRemoveAccountSetListWithRetailStore(goodsSupplier, retailStoreId, this.emptyOptions());

				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
				return goodsSupplier;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, String productName, String productDescription, String productUnit,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);

		
		checkerOf(userContext).checkProductNameOfSupplierProduct(productName);
		
		checkerOf(userContext).checkProductDescriptionOfSupplierProduct(productDescription);
		
		checkerOf(userContext).checkProductUnitOfSupplierProduct(productUnit);
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);

	
	}
	public  GoodsSupplier addSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, String productName, String productDescription, String productUnit, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSupplierProduct(userContext,goodsSupplierId,productName, productDescription, productUnit,tokensExpr);
		
		SupplierProduct supplierProduct = createSupplierProduct(userContext,productName, productDescription, productUnit);
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			goodsSupplier.addSupplierProduct( supplierProduct );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplierProductList().done());
			
			userContext.getManagerGroup().getSupplierProductManager().onNewInstanceCreated(userContext, supplierProduct);
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSupplierProductProperties(RetailscmUserContext userContext, String goodsSupplierId,String id,String productName,String productDescription,String productUnit,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplierProduct(id);
		
		checkerOf(userContext).checkProductNameOfSupplierProduct( productName);
		checkerOf(userContext).checkProductDescriptionOfSupplierProduct( productDescription);
		checkerOf(userContext).checkProductUnitOfSupplierProduct( productUnit);

		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
		
	}
	public  GoodsSupplier updateSupplierProductProperties(RetailscmUserContext userContext, String goodsSupplierId, String id,String productName,String productDescription,String productUnit, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSupplierProductProperties(userContext,goodsSupplierId,id,productName,productDescription,productUnit,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSupplierProductListList()
				.searchSupplierProductListWith(SupplierProduct.ID_PROPERTY, "is", id).done();
		
		GoodsSupplier goodsSupplierToUpdate = loadGoodsSupplier(userContext, goodsSupplierId, options);
		
		if(goodsSupplierToUpdate.getSupplierProductList().isEmpty()){
			throw new GoodsSupplierManagerException("SupplierProduct is NOT FOUND with id: '"+id+"'");
		}
		
		SupplierProduct item = goodsSupplierToUpdate.getSupplierProductList().first();
		
		item.updateProductName( productName );
		item.updateProductDescription( productDescription );
		item.updateProductUnit( productUnit );

		
		//checkParamsForAddingSupplierProduct(userContext,goodsSupplierId,name, code, used,tokensExpr);
		GoodsSupplier goodsSupplier = saveGoodsSupplier(userContext, goodsSupplierToUpdate, tokens().withSupplierProductList().done());
		synchronized(goodsSupplier){ 
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SupplierProduct createSupplierProduct(RetailscmUserContext userContext, String productName, String productDescription, String productUnit) throws Exception{

		SupplierProduct supplierProduct = new SupplierProduct();
		
		
		supplierProduct.setProductName(productName);		
		supplierProduct.setProductDescription(productDescription);		
		supplierProduct.setProductUnit(productUnit);
	
		
		return supplierProduct;
	
		
	}
	
	protected SupplierProduct createIndexedSupplierProduct(String id, int version){

		SupplierProduct supplierProduct = new SupplierProduct();
		supplierProduct.setId(id);
		supplierProduct.setVersion(version);
		return supplierProduct;			
		
	}
	
	protected void checkParamsForRemovingSupplierProductList(RetailscmUserContext userContext, String goodsSupplierId, 
			String supplierProductIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		for(String supplierProductIdItem: supplierProductIds){
			checkerOf(userContext).checkIdOfSupplierProduct(supplierProductIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
		
	}
	public  GoodsSupplier removeSupplierProductList(RetailscmUserContext userContext, String goodsSupplierId, 
			String supplierProductIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSupplierProductList(userContext, goodsSupplierId,  supplierProductIds, tokensExpr);
			
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
			synchronized(goodsSupplier){ 
				//Will be good when the goodsSupplier loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				goodsSupplierDaoOf(userContext).planToRemoveSupplierProductList(goodsSupplier, supplierProductIds, allTokens());
				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplierProductList().done());
				deleteRelationListInGraph(userContext, goodsSupplier.getSupplierProductList());
				return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplierProductId, int supplierProductVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGoodsSupplier( goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplierProduct(supplierProductId);
		checkerOf(userContext).checkVersionOfSupplierProduct(supplierProductVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	public  GoodsSupplier removeSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplierProductId, int supplierProductVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSupplierProduct(userContext,goodsSupplierId, supplierProductId, supplierProductVersion,tokensExpr);
		
		SupplierProduct supplierProduct = createIndexedSupplierProduct(supplierProductId, supplierProductVersion);
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			goodsSupplier.removeSupplierProduct( supplierProduct );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplierProductList().done());
			deleteRelationInGraph(userContext, supplierProduct);
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplierProductId, int supplierProductVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGoodsSupplier( goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplierProduct(supplierProductId);
		checkerOf(userContext).checkVersionOfSupplierProduct(supplierProductVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	public  GoodsSupplier copySupplierProductFrom(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplierProductId, int supplierProductVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSupplierProduct(userContext,goodsSupplierId, supplierProductId, supplierProductVersion,tokensExpr);
		
		SupplierProduct supplierProduct = createIndexedSupplierProduct(supplierProductId, supplierProductVersion);
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			goodsSupplier.copySupplierProductFrom( supplierProduct );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplierProductList().done());
			
			userContext.getManagerGroup().getSupplierProductManager().onNewInstanceCreated(userContext, (SupplierProduct)goodsSupplier.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, String supplierProductId, int supplierProductVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplierProduct(supplierProductId);
		checkerOf(userContext).checkVersionOfSupplierProduct(supplierProductVersion);
		

		if(SupplierProduct.PRODUCT_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkProductNameOfSupplierProduct(parseString(newValueExpr));
		}
		
		if(SupplierProduct.PRODUCT_DESCRIPTION_PROPERTY.equals(property)){
			checkerOf(userContext).checkProductDescriptionOfSupplierProduct(parseString(newValueExpr));
		}
		
		if(SupplierProduct.PRODUCT_UNIT_PROPERTY.equals(property)){
			checkerOf(userContext).checkProductUnitOfSupplierProduct(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	
	public  GoodsSupplier updateSupplierProduct(RetailscmUserContext userContext, String goodsSupplierId, String supplierProductId, int supplierProductVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSupplierProduct(userContext, goodsSupplierId, supplierProductId, supplierProductVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSupplierProductList().searchSupplierProductListWith(SupplierProduct.ID_PROPERTY, "eq", supplierProductId).done();
		
		
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, loadTokens);
		
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//goodsSupplier.removeSupplierProduct( supplierProduct );	
			//make changes to AcceleraterAccount.
			SupplierProduct supplierProductIndex = createIndexedSupplierProduct(supplierProductId, supplierProductVersion);
		
			SupplierProduct supplierProduct = goodsSupplier.findTheSupplierProduct(supplierProductIndex);
			if(supplierProduct == null){
				throw new GoodsSupplierManagerException(supplierProduct+" is NOT FOUND" );
			}
			
			supplierProduct.changeProperty(property, newValueExpr);
			
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplierProductList().done());
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, String buyerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String pickingId, String shipmentId, String deliveryId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);

		
		checkerOf(userContext).checkBuyerIdOfSupplyOrder(buyerId);
		
		checkerOf(userContext).checkTitleOfSupplyOrder(title);
		
		checkerOf(userContext).checkTotalAmountOfSupplyOrder(totalAmount);
		
		checkerOf(userContext).checkConfirmationIdOfSupplyOrder(confirmationId);
		
		checkerOf(userContext).checkApprovalIdOfSupplyOrder(approvalId);
		
		checkerOf(userContext).checkProcessingIdOfSupplyOrder(processingId);
		
		checkerOf(userContext).checkPickingIdOfSupplyOrder(pickingId);
		
		checkerOf(userContext).checkShipmentIdOfSupplyOrder(shipmentId);
		
		checkerOf(userContext).checkDeliveryIdOfSupplyOrder(deliveryId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);

	
	}
	public  GoodsSupplier addSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, String buyerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String pickingId, String shipmentId, String deliveryId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSupplyOrder(userContext,goodsSupplierId,buyerId, title, totalAmount, confirmationId, approvalId, processingId, pickingId, shipmentId, deliveryId,tokensExpr);
		
		SupplyOrder supplyOrder = createSupplyOrder(userContext,buyerId, title, totalAmount, confirmationId, approvalId, processingId, pickingId, shipmentId, deliveryId);
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			goodsSupplier.addSupplyOrder( supplyOrder );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
			
			userContext.getManagerGroup().getSupplyOrderManager().onNewInstanceCreated(userContext, supplyOrder);
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSupplyOrderProperties(RetailscmUserContext userContext, String goodsSupplierId,String id,String title,BigDecimal totalAmount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplyOrder(id);
		
		checkerOf(userContext).checkTitleOfSupplyOrder( title);
		checkerOf(userContext).checkTotalAmountOfSupplyOrder( totalAmount);

		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
		
	}
	public  GoodsSupplier updateSupplyOrderProperties(RetailscmUserContext userContext, String goodsSupplierId, String id,String title,BigDecimal totalAmount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSupplyOrderProperties(userContext,goodsSupplierId,id,title,totalAmount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSupplyOrderListList()
				.searchSupplyOrderListWith(SupplyOrder.ID_PROPERTY, "is", id).done();
		
		GoodsSupplier goodsSupplierToUpdate = loadGoodsSupplier(userContext, goodsSupplierId, options);
		
		if(goodsSupplierToUpdate.getSupplyOrderList().isEmpty()){
			throw new GoodsSupplierManagerException("SupplyOrder is NOT FOUND with id: '"+id+"'");
		}
		
		SupplyOrder item = goodsSupplierToUpdate.getSupplyOrderList().first();
		
		item.updateTitle( title );
		item.updateTotalAmount( totalAmount );

		
		//checkParamsForAddingSupplyOrder(userContext,goodsSupplierId,name, code, used,tokensExpr);
		GoodsSupplier goodsSupplier = saveGoodsSupplier(userContext, goodsSupplierToUpdate, tokens().withSupplyOrderList().done());
		synchronized(goodsSupplier){ 
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SupplyOrder createSupplyOrder(RetailscmUserContext userContext, String buyerId, String title, BigDecimal totalAmount, String confirmationId, String approvalId, String processingId, String pickingId, String shipmentId, String deliveryId) throws Exception{

		SupplyOrder supplyOrder = new SupplyOrder();
		
		
		RetailStoreCountryCenter  buyer = new RetailStoreCountryCenter();
		buyer.setId(buyerId);		
		supplyOrder.setBuyer(buyer);		
		supplyOrder.setTitle(title);		
		supplyOrder.setTotalAmount(totalAmount);		
		SupplyOrderConfirmation  confirmation = new SupplyOrderConfirmation();
		confirmation.setId(confirmationId);		
		supplyOrder.setConfirmation(confirmation);		
		SupplyOrderApproval  approval = new SupplyOrderApproval();
		approval.setId(approvalId);		
		supplyOrder.setApproval(approval);		
		SupplyOrderProcessing  processing = new SupplyOrderProcessing();
		processing.setId(processingId);		
		supplyOrder.setProcessing(processing);		
		SupplyOrderPicking  picking = new SupplyOrderPicking();
		picking.setId(pickingId);		
		supplyOrder.setPicking(picking);		
		SupplyOrderShipment  shipment = new SupplyOrderShipment();
		shipment.setId(shipmentId);		
		supplyOrder.setShipment(shipment);		
		SupplyOrderDelivery  delivery = new SupplyOrderDelivery();
		delivery.setId(deliveryId);		
		supplyOrder.setDelivery(delivery);		
		supplyOrder.setLastUpdateTime(userContext.now());
	
		
		return supplyOrder;
	
		
	}
	
	protected SupplyOrder createIndexedSupplyOrder(String id, int version){

		SupplyOrder supplyOrder = new SupplyOrder();
		supplyOrder.setId(id);
		supplyOrder.setVersion(version);
		return supplyOrder;			
		
	}
	
	protected void checkParamsForRemovingSupplyOrderList(RetailscmUserContext userContext, String goodsSupplierId, 
			String supplyOrderIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		for(String supplyOrderIdItem: supplyOrderIds){
			checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
		
	}
	public  GoodsSupplier removeSupplyOrderList(RetailscmUserContext userContext, String goodsSupplierId, 
			String supplyOrderIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSupplyOrderList(userContext, goodsSupplierId,  supplyOrderIds, tokensExpr);
			
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
			synchronized(goodsSupplier){ 
				//Will be good when the goodsSupplier loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				goodsSupplierDaoOf(userContext).planToRemoveSupplyOrderList(goodsSupplier, supplyOrderIds, allTokens());
				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
				deleteRelationListInGraph(userContext, goodsSupplier.getSupplyOrderList());
				return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGoodsSupplier( goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	public  GoodsSupplier removeSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSupplyOrder(userContext,goodsSupplierId, supplyOrderId, supplyOrderVersion,tokensExpr);
		
		SupplyOrder supplyOrder = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			goodsSupplier.removeSupplyOrder( supplyOrder );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
			deleteRelationInGraph(userContext, supplyOrder);
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGoodsSupplier( goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	public  GoodsSupplier copySupplyOrderFrom(RetailscmUserContext userContext, String goodsSupplierId, 
		String supplyOrderId, int supplyOrderVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSupplyOrder(userContext,goodsSupplierId, supplyOrderId, supplyOrderVersion,tokensExpr);
		
		SupplyOrder supplyOrder = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			supplyOrder.updateLastUpdateTime(userContext.now());
			
			goodsSupplier.copySupplyOrderFrom( supplyOrder );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
			
			userContext.getManagerGroup().getSupplyOrderManager().onNewInstanceCreated(userContext, (SupplyOrder)goodsSupplier.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, String supplyOrderId, int supplyOrderVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkIdOfSupplyOrder(supplyOrderId);
		checkerOf(userContext).checkVersionOfSupplyOrder(supplyOrderVersion);
		

		if(SupplyOrder.TITLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkTitleOfSupplyOrder(parseString(newValueExpr));
		}
		
		if(SupplyOrder.TOTAL_AMOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkTotalAmountOfSupplyOrder(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	
	public  GoodsSupplier updateSupplyOrder(RetailscmUserContext userContext, String goodsSupplierId, String supplyOrderId, int supplyOrderVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSupplyOrder(userContext, goodsSupplierId, supplyOrderId, supplyOrderVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSupplyOrderList().searchSupplyOrderListWith(SupplyOrder.ID_PROPERTY, "eq", supplyOrderId).done();
		
		
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, loadTokens);
		
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//goodsSupplier.removeSupplyOrder( supplyOrder );	
			//make changes to AcceleraterAccount.
			SupplyOrder supplyOrderIndex = createIndexedSupplyOrder(supplyOrderId, supplyOrderVersion);
		
			SupplyOrder supplyOrder = goodsSupplier.findTheSupplyOrder(supplyOrderIndex);
			if(supplyOrder == null){
				throw new GoodsSupplierManagerException(supplyOrder+" is NOT FOUND" );
			}
			
			supplyOrder.changeProperty(property, newValueExpr);
			supplyOrder.updateLastUpdateTime(userContext.now());
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withSupplyOrderList().done());
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingAccountSet(RetailscmUserContext userContext, String goodsSupplierId, String name, String yearSet, Date effectiveDate, String accountingSystem, String domesticCurrencyCode, String domesticCurrencyName, String openingBank, long accountNumber, String countryCenterId, String retailStoreId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);

		
		checkerOf(userContext).checkNameOfAccountSet(name);
		
		checkerOf(userContext).checkYearSetOfAccountSet(yearSet);
		
		checkerOf(userContext).checkEffectiveDateOfAccountSet(effectiveDate);
		
		checkerOf(userContext).checkAccountingSystemOfAccountSet(accountingSystem);
		
		checkerOf(userContext).checkDomesticCurrencyCodeOfAccountSet(domesticCurrencyCode);
		
		checkerOf(userContext).checkDomesticCurrencyNameOfAccountSet(domesticCurrencyName);
		
		checkerOf(userContext).checkOpeningBankOfAccountSet(openingBank);
		
		checkerOf(userContext).checkAccountNumberOfAccountSet(accountNumber);
		
		checkerOf(userContext).checkCountryCenterIdOfAccountSet(countryCenterId);
		
		checkerOf(userContext).checkRetailStoreIdOfAccountSet(retailStoreId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);

	
	}
	public  GoodsSupplier addAccountSet(RetailscmUserContext userContext, String goodsSupplierId, String name, String yearSet, Date effectiveDate, String accountingSystem, String domesticCurrencyCode, String domesticCurrencyName, String openingBank, long accountNumber, String countryCenterId, String retailStoreId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingAccountSet(userContext,goodsSupplierId,name, yearSet, effectiveDate, accountingSystem, domesticCurrencyCode, domesticCurrencyName, openingBank, accountNumber, countryCenterId, retailStoreId,tokensExpr);
		
		AccountSet accountSet = createAccountSet(userContext,name, yearSet, effectiveDate, accountingSystem, domesticCurrencyCode, domesticCurrencyName, openingBank, accountNumber, countryCenterId, retailStoreId);
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			goodsSupplier.addAccountSet( accountSet );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
			
			userContext.getManagerGroup().getAccountSetManager().onNewInstanceCreated(userContext, accountSet);
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingAccountSetProperties(RetailscmUserContext userContext, String goodsSupplierId,String id,String name,String yearSet,Date effectiveDate,String accountingSystem,String domesticCurrencyCode,String domesticCurrencyName,String openingBank,long accountNumber,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkIdOfAccountSet(id);
		
		checkerOf(userContext).checkNameOfAccountSet( name);
		checkerOf(userContext).checkYearSetOfAccountSet( yearSet);
		checkerOf(userContext).checkEffectiveDateOfAccountSet( effectiveDate);
		checkerOf(userContext).checkAccountingSystemOfAccountSet( accountingSystem);
		checkerOf(userContext).checkDomesticCurrencyCodeOfAccountSet( domesticCurrencyCode);
		checkerOf(userContext).checkDomesticCurrencyNameOfAccountSet( domesticCurrencyName);
		checkerOf(userContext).checkOpeningBankOfAccountSet( openingBank);
		checkerOf(userContext).checkAccountNumberOfAccountSet( accountNumber);

		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
		
	}
	public  GoodsSupplier updateAccountSetProperties(RetailscmUserContext userContext, String goodsSupplierId, String id,String name,String yearSet,Date effectiveDate,String accountingSystem,String domesticCurrencyCode,String domesticCurrencyName,String openingBank,long accountNumber, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingAccountSetProperties(userContext,goodsSupplierId,id,name,yearSet,effectiveDate,accountingSystem,domesticCurrencyCode,domesticCurrencyName,openingBank,accountNumber,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withAccountSetListList()
				.searchAccountSetListWith(AccountSet.ID_PROPERTY, "is", id).done();
		
		GoodsSupplier goodsSupplierToUpdate = loadGoodsSupplier(userContext, goodsSupplierId, options);
		
		if(goodsSupplierToUpdate.getAccountSetList().isEmpty()){
			throw new GoodsSupplierManagerException("AccountSet is NOT FOUND with id: '"+id+"'");
		}
		
		AccountSet item = goodsSupplierToUpdate.getAccountSetList().first();
		
		item.updateName( name );
		item.updateYearSet( yearSet );
		item.updateEffectiveDate( effectiveDate );
		item.updateAccountingSystem( accountingSystem );
		item.updateDomesticCurrencyCode( domesticCurrencyCode );
		item.updateDomesticCurrencyName( domesticCurrencyName );
		item.updateOpeningBank( openingBank );
		item.updateAccountNumber( accountNumber );

		
		//checkParamsForAddingAccountSet(userContext,goodsSupplierId,name, code, used,tokensExpr);
		GoodsSupplier goodsSupplier = saveGoodsSupplier(userContext, goodsSupplierToUpdate, tokens().withAccountSetList().done());
		synchronized(goodsSupplier){ 
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected AccountSet createAccountSet(RetailscmUserContext userContext, String name, String yearSet, Date effectiveDate, String accountingSystem, String domesticCurrencyCode, String domesticCurrencyName, String openingBank, long accountNumber, String countryCenterId, String retailStoreId) throws Exception{

		AccountSet accountSet = new AccountSet();
		
		
		accountSet.setName(name);		
		accountSet.setYearSet(yearSet);		
		accountSet.setEffectiveDate(effectiveDate);		
		accountSet.setAccountingSystem(accountingSystem);		
		accountSet.setDomesticCurrencyCode(domesticCurrencyCode);		
		accountSet.setDomesticCurrencyName(domesticCurrencyName);		
		accountSet.setOpeningBank(openingBank);		
		accountSet.setAccountNumber(accountNumber);		
		RetailStoreCountryCenter  countryCenter = new RetailStoreCountryCenter();
		countryCenter.setId(countryCenterId);		
		accountSet.setCountryCenter(countryCenter);		
		RetailStore  retailStore = new RetailStore();
		retailStore.setId(retailStoreId);		
		accountSet.setRetailStore(retailStore);		
		accountSet.setLastUpdateTime(userContext.now());
	
		
		return accountSet;
	
		
	}
	
	protected AccountSet createIndexedAccountSet(String id, int version){

		AccountSet accountSet = new AccountSet();
		accountSet.setId(id);
		accountSet.setVersion(version);
		return accountSet;			
		
	}
	
	protected void checkParamsForRemovingAccountSetList(RetailscmUserContext userContext, String goodsSupplierId, 
			String accountSetIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		for(String accountSetIdItem: accountSetIds){
			checkerOf(userContext).checkIdOfAccountSet(accountSetIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
		
	}
	public  GoodsSupplier removeAccountSetList(RetailscmUserContext userContext, String goodsSupplierId, 
			String accountSetIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingAccountSetList(userContext, goodsSupplierId,  accountSetIds, tokensExpr);
			
			
			GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
			synchronized(goodsSupplier){ 
				//Will be good when the goodsSupplier loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				goodsSupplierDaoOf(userContext).planToRemoveAccountSetList(goodsSupplier, accountSetIds, allTokens());
				goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
				deleteRelationListInGraph(userContext, goodsSupplier.getAccountSetList());
				return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingAccountSet(RetailscmUserContext userContext, String goodsSupplierId, 
		String accountSetId, int accountSetVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGoodsSupplier( goodsSupplierId);
		checkerOf(userContext).checkIdOfAccountSet(accountSetId);
		checkerOf(userContext).checkVersionOfAccountSet(accountSetVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	public  GoodsSupplier removeAccountSet(RetailscmUserContext userContext, String goodsSupplierId, 
		String accountSetId, int accountSetVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingAccountSet(userContext,goodsSupplierId, accountSetId, accountSetVersion,tokensExpr);
		
		AccountSet accountSet = createIndexedAccountSet(accountSetId, accountSetVersion);
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			goodsSupplier.removeAccountSet( accountSet );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
			deleteRelationInGraph(userContext, accountSet);
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingAccountSet(RetailscmUserContext userContext, String goodsSupplierId, 
		String accountSetId, int accountSetVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGoodsSupplier( goodsSupplierId);
		checkerOf(userContext).checkIdOfAccountSet(accountSetId);
		checkerOf(userContext).checkVersionOfAccountSet(accountSetVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	public  GoodsSupplier copyAccountSetFrom(RetailscmUserContext userContext, String goodsSupplierId, 
		String accountSetId, int accountSetVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingAccountSet(userContext,goodsSupplierId, accountSetId, accountSetVersion,tokensExpr);
		
		AccountSet accountSet = createIndexedAccountSet(accountSetId, accountSetVersion);
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, allTokens());
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			accountSet.updateLastUpdateTime(userContext.now());
			
			goodsSupplier.copyAccountSetFrom( accountSet );		
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
			
			userContext.getManagerGroup().getAccountSetManager().onNewInstanceCreated(userContext, (AccountSet)goodsSupplier.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingAccountSet(RetailscmUserContext userContext, String goodsSupplierId, String accountSetId, int accountSetVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfGoodsSupplier(goodsSupplierId);
		checkerOf(userContext).checkIdOfAccountSet(accountSetId);
		checkerOf(userContext).checkVersionOfAccountSet(accountSetVersion);
		

		if(AccountSet.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfAccountSet(parseString(newValueExpr));
		}
		
		if(AccountSet.YEAR_SET_PROPERTY.equals(property)){
			checkerOf(userContext).checkYearSetOfAccountSet(parseString(newValueExpr));
		}
		
		if(AccountSet.EFFECTIVE_DATE_PROPERTY.equals(property)){
			checkerOf(userContext).checkEffectiveDateOfAccountSet(parseDate(newValueExpr));
		}
		
		if(AccountSet.ACCOUNTING_SYSTEM_PROPERTY.equals(property)){
			checkerOf(userContext).checkAccountingSystemOfAccountSet(parseString(newValueExpr));
		}
		
		if(AccountSet.DOMESTIC_CURRENCY_CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkDomesticCurrencyCodeOfAccountSet(parseString(newValueExpr));
		}
		
		if(AccountSet.DOMESTIC_CURRENCY_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkDomesticCurrencyNameOfAccountSet(parseString(newValueExpr));
		}
		
		if(AccountSet.OPENING_BANK_PROPERTY.equals(property)){
			checkerOf(userContext).checkOpeningBankOfAccountSet(parseString(newValueExpr));
		}
		
		if(AccountSet.ACCOUNT_NUMBER_PROPERTY.equals(property)){
			checkerOf(userContext).checkAccountNumberOfAccountSet(parseLong(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(GoodsSupplierManagerException.class);
	
	}
	
	public  GoodsSupplier updateAccountSet(RetailscmUserContext userContext, String goodsSupplierId, String accountSetId, int accountSetVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingAccountSet(userContext, goodsSupplierId, accountSetId, accountSetVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withAccountSetList().searchAccountSetListWith(AccountSet.ID_PROPERTY, "eq", accountSetId).done();
		
		
		
		GoodsSupplier goodsSupplier = loadGoodsSupplier(userContext, goodsSupplierId, loadTokens);
		
		synchronized(goodsSupplier){ 
			//Will be good when the goodsSupplier loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//goodsSupplier.removeAccountSet( accountSet );	
			//make changes to AcceleraterAccount.
			AccountSet accountSetIndex = createIndexedAccountSet(accountSetId, accountSetVersion);
		
			AccountSet accountSet = goodsSupplier.findTheAccountSet(accountSetIndex);
			if(accountSet == null){
				throw new GoodsSupplierManagerException(accountSet+" is NOT FOUND" );
			}
			
			accountSet.changeProperty(property, newValueExpr);
			accountSet.updateLastUpdateTime(userContext.now());
			goodsSupplier = saveGoodsSupplier(userContext, goodsSupplier, tokens().withAccountSetList().done());
			return present(userContext,goodsSupplier, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(RetailscmUserContext userContext, GoodsSupplier newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


