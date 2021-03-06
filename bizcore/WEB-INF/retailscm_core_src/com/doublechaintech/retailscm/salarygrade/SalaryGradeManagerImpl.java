
package com.doublechaintech.retailscm.salarygrade;

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
import com.doublechaintech.retailscm.employee.Employee;
import com.doublechaintech.retailscm.employeesalarysheet.EmployeeSalarySheet;

import com.doublechaintech.retailscm.retailstorecountrycenter.CandidateRetailStoreCountryCenter;

import com.doublechaintech.retailscm.hrinterview.HrInterview;
import com.doublechaintech.retailscm.retailstorecountrycenter.RetailStoreCountryCenter;
import com.doublechaintech.retailscm.professioninterview.ProfessionInterview;
import com.doublechaintech.retailscm.offeracceptance.OfferAcceptance;
import com.doublechaintech.retailscm.employeeboarding.EmployeeBoarding;
import com.doublechaintech.retailscm.payingoff.PayingOff;
import com.doublechaintech.retailscm.levelthreedepartment.LevelThreeDepartment;
import com.doublechaintech.retailscm.occupationtype.OccupationType;
import com.doublechaintech.retailscm.salarygrade.SalaryGrade;
import com.doublechaintech.retailscm.offerapproval.OfferApproval;
import com.doublechaintech.retailscm.termination.Termination;
import com.doublechaintech.retailscm.jobapplication.JobApplication;
import com.doublechaintech.retailscm.employee.Employee;
import com.doublechaintech.retailscm.responsibilitytype.ResponsibilityType;






public class SalaryGradeManagerImpl extends CustomRetailscmCheckerManager implements SalaryGradeManager {
	
	private static final String SERVICE_TYPE = "SalaryGrade";
	@Override
	public SalaryGradeDAO daoOf(RetailscmUserContext userContext) {
		return salaryGradeDaoOf(userContext);
	}
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws SalaryGradeManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new SalaryGradeManagerException(message);

	}
	
	

 	protected SalaryGrade saveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade, String [] tokensExpr) throws Exception{	
 		//return getSalaryGradeDAO().save(salaryGrade, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSalaryGrade(userContext, salaryGrade, tokens);
 	}
 	
 	protected SalaryGrade saveSalaryGradeDetail(RetailscmUserContext userContext, SalaryGrade salaryGrade) throws Exception{	

 		
 		return saveSalaryGrade(userContext, salaryGrade, allTokens());
 	}
 	
 	public SalaryGrade loadSalaryGrade(RetailscmUserContext userContext, String salaryGradeId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).throwExceptionIfHasErrors( SalaryGradeManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,salaryGrade, tokens);
 	}
 	
 	
 	 public SalaryGrade searchSalaryGrade(RetailscmUserContext userContext, String salaryGradeId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).throwExceptionIfHasErrors( SalaryGradeManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,salaryGrade, tokens);
 	}
 	
 	

 	protected SalaryGrade present(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,salaryGrade,tokens);
		
		
		SalaryGrade  salaryGradeToPresent = salaryGradeDaoOf(userContext).present(salaryGrade, tokens);
		
		List<BaseEntity> entityListToNaming = salaryGradeToPresent.collectRefercencesFromLists();
		salaryGradeDaoOf(userContext).alias(entityListToNaming);
		
		return  salaryGradeToPresent;
		
		
	}
 
 	
 	
 	public SalaryGrade loadSalaryGradeDetail(RetailscmUserContext userContext, String salaryGradeId) throws Exception{	
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, allTokens());
 		return present(userContext,salaryGrade, allTokens());
		
 	}
 	
 	public Object view(RetailscmUserContext userContext, String salaryGradeId) throws Exception{	
 		SalaryGrade salaryGrade = loadSalaryGrade( userContext, salaryGradeId, viewTokens());
 		return present(userContext,salaryGrade, allTokens());
		
 	}
 	protected SalaryGrade saveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String,Object>tokens) throws Exception{	
 		return salaryGradeDaoOf(userContext).save(salaryGrade, tokens);
 	}
 	protected SalaryGrade loadSalaryGrade(RetailscmUserContext userContext, String salaryGradeId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).throwExceptionIfHasErrors( SalaryGradeManagerException.class);

 
 		return salaryGradeDaoOf(userContext).load(salaryGradeId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String, Object> tokens){
		super.addActions(userContext, salaryGrade, tokens);
		
		addAction(userContext, salaryGrade, tokens,"@create","createSalaryGrade","createSalaryGrade/","main","primary");
		addAction(userContext, salaryGrade, tokens,"@update","updateSalaryGrade","updateSalaryGrade/"+salaryGrade.getId()+"/","main","primary");
		addAction(userContext, salaryGrade, tokens,"@copy","cloneSalaryGrade","cloneSalaryGrade/"+salaryGrade.getId()+"/","main","primary");
		
		addAction(userContext, salaryGrade, tokens,"salary_grade.transfer_to_company","transferToAnotherCompany","transferToAnotherCompany/"+salaryGrade.getId()+"/","main","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.addEmployee","addEmployee","addEmployee/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.removeEmployee","removeEmployee","removeEmployee/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.updateEmployee","updateEmployee","updateEmployee/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.copyEmployeeFrom","copyEmployeeFrom","copyEmployeeFrom/"+salaryGrade.getId()+"/","employeeList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.addEmployeeSalarySheet","addEmployeeSalarySheet","addEmployeeSalarySheet/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.removeEmployeeSalarySheet","removeEmployeeSalarySheet","removeEmployeeSalarySheet/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.updateEmployeeSalarySheet","updateEmployeeSalarySheet","updateEmployeeSalarySheet/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
		addAction(userContext, salaryGrade, tokens,"salary_grade.copyEmployeeSalarySheetFrom","copyEmployeeSalarySheetFrom","copyEmployeeSalarySheetFrom/"+salaryGrade.getId()+"/","employeeSalarySheetList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public SalaryGrade createSalaryGrade(RetailscmUserContext userContext, String code,String companyId,String name,String detailDescription) throws Exception
	//public SalaryGrade createSalaryGrade(RetailscmUserContext userContext,String code, String companyId, String name, String detailDescription) throws Exception
	{
		
		

		

		checkerOf(userContext).checkCodeOfSalaryGrade(code);
		checkerOf(userContext).checkNameOfSalaryGrade(name);
		checkerOf(userContext).checkDetailDescriptionOfSalaryGrade(detailDescription);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);


		SalaryGrade salaryGrade=createNewSalaryGrade();	

		salaryGrade.setCode(code);
			
		RetailStoreCountryCenter company = loadRetailStoreCountryCenter(userContext, companyId,emptyOptions());
		salaryGrade.setCompany(company);
		
		
		salaryGrade.setName(name);
		salaryGrade.setDetailDescription(detailDescription);

		salaryGrade = saveSalaryGrade(userContext, salaryGrade, emptyOptions());
		
		onNewInstanceCreated(userContext, salaryGrade);
		return salaryGrade;

		
	}
	protected SalaryGrade createNewSalaryGrade() 
	{
		
		return new SalaryGrade();		
	}
	
	protected void checkParamsForUpdatingSalaryGrade(RetailscmUserContext userContext,String salaryGradeId, int salaryGradeVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkVersionOfSalaryGrade( salaryGradeVersion);
		

		if(SalaryGrade.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfSalaryGrade(parseString(newValueExpr));
		}		

		
		if(SalaryGrade.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfSalaryGrade(parseString(newValueExpr));
		}
		if(SalaryGrade.DETAIL_DESCRIPTION_PROPERTY.equals(property)){
			checkerOf(userContext).checkDetailDescriptionOfSalaryGrade(parseString(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
		
	}
	
	
	
	public SalaryGrade clone(RetailscmUserContext userContext, String fromSalaryGradeId) throws Exception{
		
		return salaryGradeDaoOf(userContext).clone(fromSalaryGradeId, this.allTokens());
	}
	
	public SalaryGrade internalSaveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade) throws Exception 
	{
		return internalSaveSalaryGrade(userContext, salaryGrade, allTokens());

	}
	public SalaryGrade internalSaveSalaryGrade(RetailscmUserContext userContext, SalaryGrade salaryGrade, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingSalaryGrade(userContext, salaryGradeId, salaryGradeVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(salaryGrade){ 
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SalaryGrade.
			if (salaryGrade.isChanged()){
			
			}
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, options);
			return salaryGrade;
			
		}

	}
	
	public SalaryGrade updateSalaryGrade(RetailscmUserContext userContext,String salaryGradeId, int salaryGradeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSalaryGrade(userContext, salaryGradeId, salaryGradeVersion, property, newValueExpr, tokensExpr);
		
		
		
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		if(salaryGrade.getVersion() != salaryGradeVersion){
			String message = "The target version("+salaryGrade.getVersion()+") is not equals to version("+salaryGradeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(salaryGrade){ 
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SalaryGrade.
			
			salaryGrade.changeProperty(property, newValueExpr);
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			//return saveSalaryGrade(userContext, salaryGrade, tokens().done());
		}

	}
	
	public SalaryGrade updateSalaryGradeProperty(RetailscmUserContext userContext,String salaryGradeId, int salaryGradeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSalaryGrade(userContext, salaryGradeId, salaryGradeVersion, property, newValueExpr, tokensExpr);
		
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		if(salaryGrade.getVersion() != salaryGradeVersion){
			String message = "The target version("+salaryGrade.getVersion()+") is not equals to version("+salaryGradeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(salaryGrade){ 
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SalaryGrade.
			
			salaryGrade.changeProperty(property, newValueExpr);
			
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			//return saveSalaryGrade(userContext, salaryGrade, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected SalaryGradeTokens tokens(){
		return SalaryGradeTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SalaryGradeTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortEmployeeListWith("id","desc")
		.sortEmployeeSalarySheetListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SalaryGradeTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherCompany(RetailscmUserContext userContext, String salaryGradeId, String anotherCompanyId) throws Exception
 	{
 		
 		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
 		checkerOf(userContext).checkIdOfRetailStoreCountryCenter(anotherCompanyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
 		
 	}
 	public SalaryGrade transferToAnotherCompany(RetailscmUserContext userContext, String salaryGradeId, String anotherCompanyId) throws Exception
 	{
 		checkParamsForTransferingAnotherCompany(userContext, salaryGradeId,anotherCompanyId);
 
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());	
		synchronized(salaryGrade){
			//will be good when the salaryGrade loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			RetailStoreCountryCenter company = loadRetailStoreCountryCenter(userContext, anotherCompanyId, emptyOptions());		
			salaryGrade.updateCompany(company);		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, emptyOptions());
			
			return present(userContext,salaryGrade, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateRetailStoreCountryCenter requestCandidateCompany(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<RetailStoreCountryCenter> candidateList = retailStoreCountryCenterDaoOf(userContext).requestCandidateRetailStoreCountryCenterForSalaryGrade(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected RetailStoreCountryCenter loadRetailStoreCountryCenter(RetailscmUserContext userContext, String newCompanyId, Map<String,Object> options) throws Exception
 	{
		
 		return retailStoreCountryCenterDaoOf(userContext).load(newCompanyId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String salaryGradeId, int salaryGradeVersion) throws Exception {
		//deleteInternal(userContext, salaryGradeId, salaryGradeVersion);		
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String salaryGradeId, int salaryGradeVersion) throws Exception{
			
		salaryGradeDaoOf(userContext).delete(salaryGradeId, salaryGradeVersion);
	}
	
	public SalaryGrade forgetByAll(RetailscmUserContext userContext, String salaryGradeId, int salaryGradeVersion) throws Exception {
		return forgetByAllInternal(userContext, salaryGradeId, salaryGradeVersion);		
	}
	protected SalaryGrade forgetByAllInternal(RetailscmUserContext userContext,
			String salaryGradeId, int salaryGradeVersion) throws Exception{
			
		return salaryGradeDaoOf(userContext).disconnectFromAll(salaryGradeId, salaryGradeVersion);
	}
	
	

	
	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SalaryGradeManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return salaryGradeDaoOf(userContext).deleteAll();
	}


	//disconnect SalaryGrade with company in Employee
	protected SalaryGrade breakWithEmployeeByCompany(RetailscmUserContext userContext, String salaryGradeId, String companyId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithCompany(salaryGrade, companyId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with department in Employee
	protected SalaryGrade breakWithEmployeeByDepartment(RetailscmUserContext userContext, String salaryGradeId, String departmentId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithDepartment(salaryGrade, departmentId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with occupation in Employee
	protected SalaryGrade breakWithEmployeeByOccupation(RetailscmUserContext userContext, String salaryGradeId, String occupationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithOccupation(salaryGrade, occupationId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with responsible_for in Employee
	protected SalaryGrade breakWithEmployeeByResponsibleFor(RetailscmUserContext userContext, String salaryGradeId, String responsibleForId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithResponsibleFor(salaryGrade, responsibleForId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with job_application in Employee
	protected SalaryGrade breakWithEmployeeByJobApplication(RetailscmUserContext userContext, String salaryGradeId, String jobApplicationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithJobApplication(salaryGrade, jobApplicationId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with profession_interview in Employee
	protected SalaryGrade breakWithEmployeeByProfessionInterview(RetailscmUserContext userContext, String salaryGradeId, String professionInterviewId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithProfessionInterview(salaryGrade, professionInterviewId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with hr_interview in Employee
	protected SalaryGrade breakWithEmployeeByHrInterview(RetailscmUserContext userContext, String salaryGradeId, String hrInterviewId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithHrInterview(salaryGrade, hrInterviewId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with offer_approval in Employee
	protected SalaryGrade breakWithEmployeeByOfferApproval(RetailscmUserContext userContext, String salaryGradeId, String offerApprovalId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithOfferApproval(salaryGrade, offerApprovalId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with offer_acceptance in Employee
	protected SalaryGrade breakWithEmployeeByOfferAcceptance(RetailscmUserContext userContext, String salaryGradeId, String offerAcceptanceId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithOfferAcceptance(salaryGrade, offerAcceptanceId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with employee_boarding in Employee
	protected SalaryGrade breakWithEmployeeByEmployeeBoarding(RetailscmUserContext userContext, String salaryGradeId, String employeeBoardingId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithEmployeeBoarding(salaryGrade, employeeBoardingId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with termination in Employee
	protected SalaryGrade breakWithEmployeeByTermination(RetailscmUserContext userContext, String salaryGradeId, String terminationId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeListWithTermination(salaryGrade, terminationId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with employee in EmployeeSalarySheet
	protected SalaryGrade breakWithEmployeeSalarySheetByEmployee(RetailscmUserContext userContext, String salaryGradeId, String employeeId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeSalarySheetListWithEmployee(salaryGrade, employeeId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
				return salaryGrade;
			}
	}
	//disconnect SalaryGrade with paying_off in EmployeeSalarySheet
	protected SalaryGrade breakWithEmployeeSalarySheetByPayingOff(RetailscmUserContext userContext, String salaryGradeId, String payingOffId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());

			synchronized(salaryGrade){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				salaryGradeDaoOf(userContext).planToRemoveEmployeeSalarySheetListWithPayingOff(salaryGrade, payingOffId, this.emptyOptions());

				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
				return salaryGrade;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingEmployee(RetailscmUserContext userContext, String salaryGradeId, String companyId, String title, String departmentId, String familyName, String givenName, String email, String city, String address, String cellPhone, String occupationId, String responsibleForId, String salaryAccount, String jobApplicationId, String professionInterviewId, String hrInterviewId, String offerApprovalId, String offerAcceptanceId, String employeeBoardingId, String terminationId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);

		
		checkerOf(userContext).checkCompanyIdOfEmployee(companyId);
		
		checkerOf(userContext).checkTitleOfEmployee(title);
		
		checkerOf(userContext).checkDepartmentIdOfEmployee(departmentId);
		
		checkerOf(userContext).checkFamilyNameOfEmployee(familyName);
		
		checkerOf(userContext).checkGivenNameOfEmployee(givenName);
		
		checkerOf(userContext).checkEmailOfEmployee(email);
		
		checkerOf(userContext).checkCityOfEmployee(city);
		
		checkerOf(userContext).checkAddressOfEmployee(address);
		
		checkerOf(userContext).checkCellPhoneOfEmployee(cellPhone);
		
		checkerOf(userContext).checkOccupationIdOfEmployee(occupationId);
		
		checkerOf(userContext).checkResponsibleForIdOfEmployee(responsibleForId);
		
		checkerOf(userContext).checkSalaryAccountOfEmployee(salaryAccount);
		
		checkerOf(userContext).checkJobApplicationIdOfEmployee(jobApplicationId);
		
		checkerOf(userContext).checkProfessionInterviewIdOfEmployee(professionInterviewId);
		
		checkerOf(userContext).checkHrInterviewIdOfEmployee(hrInterviewId);
		
		checkerOf(userContext).checkOfferApprovalIdOfEmployee(offerApprovalId);
		
		checkerOf(userContext).checkOfferAcceptanceIdOfEmployee(offerAcceptanceId);
		
		checkerOf(userContext).checkEmployeeBoardingIdOfEmployee(employeeBoardingId);
		
		checkerOf(userContext).checkTerminationIdOfEmployee(terminationId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	
	}
	public  SalaryGrade addEmployee(RetailscmUserContext userContext, String salaryGradeId, String companyId, String title, String departmentId, String familyName, String givenName, String email, String city, String address, String cellPhone, String occupationId, String responsibleForId, String salaryAccount, String jobApplicationId, String professionInterviewId, String hrInterviewId, String offerApprovalId, String offerAcceptanceId, String employeeBoardingId, String terminationId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingEmployee(userContext,salaryGradeId,companyId, title, departmentId, familyName, givenName, email, city, address, cellPhone, occupationId, responsibleForId, salaryAccount, jobApplicationId, professionInterviewId, hrInterviewId, offerApprovalId, offerAcceptanceId, employeeBoardingId, terminationId,tokensExpr);
		
		Employee employee = createEmployee(userContext,companyId, title, departmentId, familyName, givenName, email, city, address, cellPhone, occupationId, responsibleForId, salaryAccount, jobApplicationId, professionInterviewId, hrInterviewId, offerApprovalId, offerAcceptanceId, employeeBoardingId, terminationId);
		
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.addEmployee( employee );		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			
			userContext.getManagerGroup().getEmployeeManager().onNewInstanceCreated(userContext, employee);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingEmployeeProperties(RetailscmUserContext userContext, String salaryGradeId,String id,String title,String familyName,String givenName,String email,String city,String address,String cellPhone,String salaryAccount,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(id);
		
		checkerOf(userContext).checkTitleOfEmployee( title);
		checkerOf(userContext).checkFamilyNameOfEmployee( familyName);
		checkerOf(userContext).checkGivenNameOfEmployee( givenName);
		checkerOf(userContext).checkEmailOfEmployee( email);
		checkerOf(userContext).checkCityOfEmployee( city);
		checkerOf(userContext).checkAddressOfEmployee( address);
		checkerOf(userContext).checkCellPhoneOfEmployee( cellPhone);
		checkerOf(userContext).checkSalaryAccountOfEmployee( salaryAccount);

		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
		
	}
	public  SalaryGrade updateEmployeeProperties(RetailscmUserContext userContext, String salaryGradeId, String id,String title,String familyName,String givenName,String email,String city,String address,String cellPhone,String salaryAccount, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingEmployeeProperties(userContext,salaryGradeId,id,title,familyName,givenName,email,city,address,cellPhone,salaryAccount,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withEmployeeListList()
				.searchEmployeeListWith(Employee.ID_PROPERTY, "is", id).done();
		
		SalaryGrade salaryGradeToUpdate = loadSalaryGrade(userContext, salaryGradeId, options);
		
		if(salaryGradeToUpdate.getEmployeeList().isEmpty()){
			throw new SalaryGradeManagerException("Employee is NOT FOUND with id: '"+id+"'");
		}
		
		Employee item = salaryGradeToUpdate.getEmployeeList().first();
		
		item.updateTitle( title );
		item.updateFamilyName( familyName );
		item.updateGivenName( givenName );
		item.updateEmail( email );
		item.updateCity( city );
		item.updateAddress( address );
		item.updateCellPhone( cellPhone );
		item.updateSalaryAccount( salaryAccount );

		
		//checkParamsForAddingEmployee(userContext,salaryGradeId,name, code, used,tokensExpr);
		SalaryGrade salaryGrade = saveSalaryGrade(userContext, salaryGradeToUpdate, tokens().withEmployeeList().done());
		synchronized(salaryGrade){ 
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Employee createEmployee(RetailscmUserContext userContext, String companyId, String title, String departmentId, String familyName, String givenName, String email, String city, String address, String cellPhone, String occupationId, String responsibleForId, String salaryAccount, String jobApplicationId, String professionInterviewId, String hrInterviewId, String offerApprovalId, String offerAcceptanceId, String employeeBoardingId, String terminationId) throws Exception{

		Employee employee = new Employee();
		
		
		RetailStoreCountryCenter  company = new RetailStoreCountryCenter();
		company.setId(companyId);		
		employee.setCompany(company);		
		employee.setTitle(title);		
		LevelThreeDepartment  department = new LevelThreeDepartment();
		department.setId(departmentId);		
		employee.setDepartment(department);		
		employee.setFamilyName(familyName);		
		employee.setGivenName(givenName);		
		employee.setEmail(email);		
		employee.setCity(city);		
		employee.setAddress(address);		
		employee.setCellPhone(cellPhone);		
		OccupationType  occupation = new OccupationType();
		occupation.setId(occupationId);		
		employee.setOccupation(occupation);		
		ResponsibilityType  responsibleFor = new ResponsibilityType();
		responsibleFor.setId(responsibleForId);		
		employee.setResponsibleFor(responsibleFor);		
		employee.setSalaryAccount(salaryAccount);		
		JobApplication  jobApplication = new JobApplication();
		jobApplication.setId(jobApplicationId);		
		employee.setJobApplication(jobApplication);		
		ProfessionInterview  professionInterview = new ProfessionInterview();
		professionInterview.setId(professionInterviewId);		
		employee.setProfessionInterview(professionInterview);		
		HrInterview  hrInterview = new HrInterview();
		hrInterview.setId(hrInterviewId);		
		employee.setHrInterview(hrInterview);		
		OfferApproval  offerApproval = new OfferApproval();
		offerApproval.setId(offerApprovalId);		
		employee.setOfferApproval(offerApproval);		
		OfferAcceptance  offerAcceptance = new OfferAcceptance();
		offerAcceptance.setId(offerAcceptanceId);		
		employee.setOfferAcceptance(offerAcceptance);		
		EmployeeBoarding  employeeBoarding = new EmployeeBoarding();
		employeeBoarding.setId(employeeBoardingId);		
		employee.setEmployeeBoarding(employeeBoarding);		
		Termination  termination = new Termination();
		termination.setId(terminationId);		
		employee.setTermination(termination);		
		employee.setLastUpdateTime(userContext.now());
	
		
		return employee;
	
		
	}
	
	protected Employee createIndexedEmployee(String id, int version){

		Employee employee = new Employee();
		employee.setId(id);
		employee.setVersion(version);
		return employee;			
		
	}
	
	protected void checkParamsForRemovingEmployeeList(RetailscmUserContext userContext, String salaryGradeId, 
			String employeeIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		for(String employeeIdItem: employeeIds){
			checkerOf(userContext).checkIdOfEmployee(employeeIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
		
	}
	public  SalaryGrade removeEmployeeList(RetailscmUserContext userContext, String salaryGradeId, 
			String employeeIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingEmployeeList(userContext, salaryGradeId,  employeeIds, tokensExpr);
			
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
			synchronized(salaryGrade){ 
				//Will be good when the salaryGrade loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				salaryGradeDaoOf(userContext).planToRemoveEmployeeList(salaryGrade, employeeIds, allTokens());
				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
				deleteRelationListInGraph(userContext, salaryGrade.getEmployeeList());
				return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingEmployee(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(employeeId);
		checkerOf(userContext).checkVersionOfEmployee(employeeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
	}
	public  SalaryGrade removeEmployee(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingEmployee(userContext,salaryGradeId, employeeId, employeeVersion,tokensExpr);
		
		Employee employee = createIndexedEmployee(employeeId, employeeVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.removeEmployee( employee );		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			deleteRelationInGraph(userContext, employee);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingEmployee(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(employeeId);
		checkerOf(userContext).checkVersionOfEmployee(employeeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
	}
	public  SalaryGrade copyEmployeeFrom(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeId, int employeeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingEmployee(userContext,salaryGradeId, employeeId, employeeVersion,tokensExpr);
		
		Employee employee = createIndexedEmployee(employeeId, employeeVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			employee.updateLastUpdateTime(userContext.now());
			
			salaryGrade.copyEmployeeFrom( employee );		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			
			userContext.getManagerGroup().getEmployeeManager().onNewInstanceCreated(userContext, (Employee)salaryGrade.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingEmployee(RetailscmUserContext userContext, String salaryGradeId, String employeeId, int employeeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployee(employeeId);
		checkerOf(userContext).checkVersionOfEmployee(employeeVersion);
		

		if(Employee.TITLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkTitleOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.FAMILY_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkFamilyNameOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.GIVEN_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkGivenNameOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.EMAIL_PROPERTY.equals(property)){
			checkerOf(userContext).checkEmailOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.CITY_PROPERTY.equals(property)){
			checkerOf(userContext).checkCityOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.ADDRESS_PROPERTY.equals(property)){
			checkerOf(userContext).checkAddressOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.CELL_PHONE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCellPhoneOfEmployee(parseString(newValueExpr));
		}
		
		if(Employee.SALARY_ACCOUNT_PROPERTY.equals(property)){
			checkerOf(userContext).checkSalaryAccountOfEmployee(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
	}
	
	public  SalaryGrade updateEmployee(RetailscmUserContext userContext, String salaryGradeId, String employeeId, int employeeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingEmployee(userContext, salaryGradeId, employeeId, employeeVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withEmployeeList().searchEmployeeListWith(Employee.ID_PROPERTY, "eq", employeeId).done();
		
		
		
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, loadTokens);
		
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//salaryGrade.removeEmployee( employee );	
			//make changes to AcceleraterAccount.
			Employee employeeIndex = createIndexedEmployee(employeeId, employeeVersion);
		
			Employee employee = salaryGrade.findTheEmployee(employeeIndex);
			if(employee == null){
				throw new SalaryGradeManagerException(employee+" is NOT FOUND" );
			}
			
			employee.changeProperty(property, newValueExpr);
			employee.updateLastUpdateTime(userContext.now());
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeList().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal reward, BigDecimal personalTax, BigDecimal socialSecurity, BigDecimal housingFound, BigDecimal jobInsurance, String payingOffId,String [] tokensExpr) throws Exception{
		
				checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);

		
		checkerOf(userContext).checkEmployeeIdOfEmployeeSalarySheet(employeeId);
		
		checkerOf(userContext).checkBaseSalaryOfEmployeeSalarySheet(baseSalary);
		
		checkerOf(userContext).checkBonusOfEmployeeSalarySheet(bonus);
		
		checkerOf(userContext).checkRewardOfEmployeeSalarySheet(reward);
		
		checkerOf(userContext).checkPersonalTaxOfEmployeeSalarySheet(personalTax);
		
		checkerOf(userContext).checkSocialSecurityOfEmployeeSalarySheet(socialSecurity);
		
		checkerOf(userContext).checkHousingFoundOfEmployeeSalarySheet(housingFound);
		
		checkerOf(userContext).checkJobInsuranceOfEmployeeSalarySheet(jobInsurance);
		
		checkerOf(userContext).checkPayingOffIdOfEmployeeSalarySheet(payingOffId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);

	
	}
	public  SalaryGrade addEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal reward, BigDecimal personalTax, BigDecimal socialSecurity, BigDecimal housingFound, BigDecimal jobInsurance, String payingOffId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingEmployeeSalarySheet(userContext,salaryGradeId,employeeId, baseSalary, bonus, reward, personalTax, socialSecurity, housingFound, jobInsurance, payingOffId,tokensExpr);
		
		EmployeeSalarySheet employeeSalarySheet = createEmployeeSalarySheet(userContext,employeeId, baseSalary, bonus, reward, personalTax, socialSecurity, housingFound, jobInsurance, payingOffId);
		
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.addEmployeeSalarySheet( employeeSalarySheet );		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			
			userContext.getManagerGroup().getEmployeeSalarySheetManager().onNewInstanceCreated(userContext, employeeSalarySheet);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingEmployeeSalarySheetProperties(RetailscmUserContext userContext, String salaryGradeId,String id,BigDecimal baseSalary,BigDecimal bonus,BigDecimal reward,BigDecimal personalTax,BigDecimal socialSecurity,BigDecimal housingFound,BigDecimal jobInsurance,String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(id);
		
		checkerOf(userContext).checkBaseSalaryOfEmployeeSalarySheet( baseSalary);
		checkerOf(userContext).checkBonusOfEmployeeSalarySheet( bonus);
		checkerOf(userContext).checkRewardOfEmployeeSalarySheet( reward);
		checkerOf(userContext).checkPersonalTaxOfEmployeeSalarySheet( personalTax);
		checkerOf(userContext).checkSocialSecurityOfEmployeeSalarySheet( socialSecurity);
		checkerOf(userContext).checkHousingFoundOfEmployeeSalarySheet( housingFound);
		checkerOf(userContext).checkJobInsuranceOfEmployeeSalarySheet( jobInsurance);

		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
		
	}
	public  SalaryGrade updateEmployeeSalarySheetProperties(RetailscmUserContext userContext, String salaryGradeId, String id,BigDecimal baseSalary,BigDecimal bonus,BigDecimal reward,BigDecimal personalTax,BigDecimal socialSecurity,BigDecimal housingFound,BigDecimal jobInsurance, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingEmployeeSalarySheetProperties(userContext,salaryGradeId,id,baseSalary,bonus,reward,personalTax,socialSecurity,housingFound,jobInsurance,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withEmployeeSalarySheetListList()
				.searchEmployeeSalarySheetListWith(EmployeeSalarySheet.ID_PROPERTY, "is", id).done();
		
		SalaryGrade salaryGradeToUpdate = loadSalaryGrade(userContext, salaryGradeId, options);
		
		if(salaryGradeToUpdate.getEmployeeSalarySheetList().isEmpty()){
			throw new SalaryGradeManagerException("EmployeeSalarySheet is NOT FOUND with id: '"+id+"'");
		}
		
		EmployeeSalarySheet item = salaryGradeToUpdate.getEmployeeSalarySheetList().first();
		
		item.updateBaseSalary( baseSalary );
		item.updateBonus( bonus );
		item.updateReward( reward );
		item.updatePersonalTax( personalTax );
		item.updateSocialSecurity( socialSecurity );
		item.updateHousingFound( housingFound );
		item.updateJobInsurance( jobInsurance );

		
		//checkParamsForAddingEmployeeSalarySheet(userContext,salaryGradeId,name, code, used,tokensExpr);
		SalaryGrade salaryGrade = saveSalaryGrade(userContext, salaryGradeToUpdate, tokens().withEmployeeSalarySheetList().done());
		synchronized(salaryGrade){ 
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected EmployeeSalarySheet createEmployeeSalarySheet(RetailscmUserContext userContext, String employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal reward, BigDecimal personalTax, BigDecimal socialSecurity, BigDecimal housingFound, BigDecimal jobInsurance, String payingOffId) throws Exception{

		EmployeeSalarySheet employeeSalarySheet = new EmployeeSalarySheet();
		
		
		Employee  employee = new Employee();
		employee.setId(employeeId);		
		employeeSalarySheet.setEmployee(employee);		
		employeeSalarySheet.setBaseSalary(baseSalary);		
		employeeSalarySheet.setBonus(bonus);		
		employeeSalarySheet.setReward(reward);		
		employeeSalarySheet.setPersonalTax(personalTax);		
		employeeSalarySheet.setSocialSecurity(socialSecurity);		
		employeeSalarySheet.setHousingFound(housingFound);		
		employeeSalarySheet.setJobInsurance(jobInsurance);		
		PayingOff  payingOff = new PayingOff();
		payingOff.setId(payingOffId);		
		employeeSalarySheet.setPayingOff(payingOff);
	
		
		return employeeSalarySheet;
	
		
	}
	
	protected EmployeeSalarySheet createIndexedEmployeeSalarySheet(String id, int version){

		EmployeeSalarySheet employeeSalarySheet = new EmployeeSalarySheet();
		employeeSalarySheet.setId(id);
		employeeSalarySheet.setVersion(version);
		return employeeSalarySheet;			
		
	}
	
	protected void checkParamsForRemovingEmployeeSalarySheetList(RetailscmUserContext userContext, String salaryGradeId, 
			String employeeSalarySheetIds[],String [] tokensExpr) throws Exception {
		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		for(String employeeSalarySheetIdItem: employeeSalarySheetIds){
			checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetIdItem);
		}
		
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
		
	}
	public  SalaryGrade removeEmployeeSalarySheetList(RetailscmUserContext userContext, String salaryGradeId, 
			String employeeSalarySheetIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingEmployeeSalarySheetList(userContext, salaryGradeId,  employeeSalarySheetIds, tokensExpr);
			
			
			SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
			synchronized(salaryGrade){ 
				//Will be good when the salaryGrade loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				salaryGradeDaoOf(userContext).planToRemoveEmployeeSalarySheetList(salaryGrade, employeeSalarySheetIds, allTokens());
				salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
				deleteRelationListInGraph(userContext, salaryGrade.getEmployeeSalarySheetList());
				return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetId);
		checkerOf(userContext).checkVersionOfEmployeeSalarySheet(employeeSalarySheetVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
	}
	public  SalaryGrade removeEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingEmployeeSalarySheet(userContext,salaryGradeId, employeeSalarySheetId, employeeSalarySheetVersion,tokensExpr);
		
		EmployeeSalarySheet employeeSalarySheet = createIndexedEmployeeSalarySheet(employeeSalarySheetId, employeeSalarySheetVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			salaryGrade.removeEmployeeSalarySheet( employeeSalarySheet );		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			deleteRelationInGraph(userContext, employeeSalarySheet);
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSalaryGrade( salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetId);
		checkerOf(userContext).checkVersionOfEmployeeSalarySheet(employeeSalarySheetVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
	}
	public  SalaryGrade copyEmployeeSalarySheetFrom(RetailscmUserContext userContext, String salaryGradeId, 
		String employeeSalarySheetId, int employeeSalarySheetVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingEmployeeSalarySheet(userContext,salaryGradeId, employeeSalarySheetId, employeeSalarySheetVersion,tokensExpr);
		
		EmployeeSalarySheet employeeSalarySheet = createIndexedEmployeeSalarySheet(employeeSalarySheetId, employeeSalarySheetVersion);
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, allTokens());
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			salaryGrade.copyEmployeeSalarySheetFrom( employeeSalarySheet );		
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			
			userContext.getManagerGroup().getEmployeeSalarySheetManager().onNewInstanceCreated(userContext, (EmployeeSalarySheet)salaryGrade.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeSalarySheetId, int employeeSalarySheetVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSalaryGrade(salaryGradeId);
		checkerOf(userContext).checkIdOfEmployeeSalarySheet(employeeSalarySheetId);
		checkerOf(userContext).checkVersionOfEmployeeSalarySheet(employeeSalarySheetVersion);
		

		if(EmployeeSalarySheet.BASE_SALARY_PROPERTY.equals(property)){
			checkerOf(userContext).checkBaseSalaryOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
		if(EmployeeSalarySheet.BONUS_PROPERTY.equals(property)){
			checkerOf(userContext).checkBonusOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
		if(EmployeeSalarySheet.REWARD_PROPERTY.equals(property)){
			checkerOf(userContext).checkRewardOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
		if(EmployeeSalarySheet.PERSONAL_TAX_PROPERTY.equals(property)){
			checkerOf(userContext).checkPersonalTaxOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
		if(EmployeeSalarySheet.SOCIAL_SECURITY_PROPERTY.equals(property)){
			checkerOf(userContext).checkSocialSecurityOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
		if(EmployeeSalarySheet.HOUSING_FOUND_PROPERTY.equals(property)){
			checkerOf(userContext).checkHousingFoundOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
		if(EmployeeSalarySheet.JOB_INSURANCE_PROPERTY.equals(property)){
			checkerOf(userContext).checkJobInsuranceOfEmployeeSalarySheet(parseBigDecimal(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SalaryGradeManagerException.class);
	
	}
	
	public  SalaryGrade updateEmployeeSalarySheet(RetailscmUserContext userContext, String salaryGradeId, String employeeSalarySheetId, int employeeSalarySheetVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingEmployeeSalarySheet(userContext, salaryGradeId, employeeSalarySheetId, employeeSalarySheetVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withEmployeeSalarySheetList().searchEmployeeSalarySheetListWith(EmployeeSalarySheet.ID_PROPERTY, "eq", employeeSalarySheetId).done();
		
		
		
		SalaryGrade salaryGrade = loadSalaryGrade(userContext, salaryGradeId, loadTokens);
		
		synchronized(salaryGrade){ 
			//Will be good when the salaryGrade loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//salaryGrade.removeEmployeeSalarySheet( employeeSalarySheet );	
			//make changes to AcceleraterAccount.
			EmployeeSalarySheet employeeSalarySheetIndex = createIndexedEmployeeSalarySheet(employeeSalarySheetId, employeeSalarySheetVersion);
		
			EmployeeSalarySheet employeeSalarySheet = salaryGrade.findTheEmployeeSalarySheet(employeeSalarySheetIndex);
			if(employeeSalarySheet == null){
				throw new SalaryGradeManagerException(employeeSalarySheet+" is NOT FOUND" );
			}
			
			employeeSalarySheet.changeProperty(property, newValueExpr);
			
			salaryGrade = saveSalaryGrade(userContext, salaryGrade, tokens().withEmployeeSalarySheetList().done());
			return present(userContext,salaryGrade, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(RetailscmUserContext userContext, SalaryGrade newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


