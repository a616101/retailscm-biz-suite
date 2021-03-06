

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Button, Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './AccountSet.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,defaultRenderAnalytics,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers,
  defaultQuickFunctions, defaultRenderSubjectList,
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(accountSet)=>{return [
	 ]}

const internalImageListOf = (accountSet) =>defaultImageListOf(accountSet,imageList)

const optionList =(accountSet)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (accountSet) =>defaultSettingListOf(accountSet, optionList)
const internalLargeTextOf = (accountSet) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const renderSettingDropDown = (cardsData,targetComponent)=>{

  return (<div style={{float: 'right'}} >
        <Dropdown overlay={renderSettingMenu(cardsData,targetComponent)} placement="bottomRight" >
       
        <Button>
        <Icon type="setting" theme="filled" twoToneColor="#00b" style={{color:'#3333b0'}}/> 设置  <Icon type="down"/>
      </Button>
      </Dropdown></div>)

}

const renderSettingMenuItem = (item,cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu.Item key={item.name}>
      <Link to={`/accountSet/${targetComponent.props.accountSet.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/accountSet/${targetComponent.props.accountSet.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (accountSet,targetComponent) =>{
	
	
	const {AccountSetService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="序号" style={{wordBreak: 'break-all'}}>{accountSet.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{accountSet.name}</Description> 
<Description term="年组" style={{wordBreak: 'break-all'}}>{accountSet.yearSet}</Description> 
<Description term="生效日期">{ moment(accountSet.effectiveDate).format('YYYY-MM-DD')}</Description> 
<Description term="会计制度" style={{wordBreak: 'break-all'}}>{accountSet.accountingSystem}</Description> 
<Description term="本币代码" style={{wordBreak: 'break-all'}}>{accountSet.domesticCurrencyCode}</Description> 
<Description term="本币名称" style={{wordBreak: 'break-all'}}>{accountSet.domesticCurrencyName}</Description> 
<Description term="开户银行" style={{wordBreak: 'break-all'}}>{accountSet.openingBank}</Description> 
<Description term="帐户号码" style={{wordBreak: 'break-all'}}>{accountSet.accountNumber}</Description> 
<Description term="双链小超">{accountSet.retailStore==null?appLocaleName(userContext,"NotAssigned"):`${accountSet.retailStore.displayName}(${accountSet.retailStore.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"双链小超","retailStore",AccountSetService.requestCandidateRetailStore,
	      AccountSetService.transferToAnotherRetailStore,"anotherRetailStoreId",accountSet.retailStore?accountSet.retailStore.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="产品供应商">{accountSet.goodsSupplier==null?appLocaleName(userContext,"NotAssigned"):`${accountSet.goodsSupplier.displayName}(${accountSet.goodsSupplier.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"产品供应商","goodsSupplier",AccountSetService.requestCandidateGoodsSupplier,
	      AccountSetService.transferToAnotherGoodsSupplier,"anotherGoodsSupplierId",accountSet.goodsSupplier?accountSet.goodsSupplier.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="最后更新时间">{ moment(accountSet.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</Description> 
	
        {buildTransferModal(accountSet,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class AccountSetDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'accountSet'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, accountingSubjectListMetaInfo, accountingPeriodListMetaInfo, accountingDocumentTypeListMetaInfo, accountingSubjectCount, accountingPeriodCount, accountingDocumentTypeCount } = this.props.accountSet
    if(!this.props.accountSet.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"账套",cardsFor: "accountSet",
    	cardsSource: this.props.accountSet,returnURL,displayName,
  		subItems: [
{name: 'accountingSubjectList', displayName:'会计科目',viewGroup:'__no_group', type:'accountingSubject',count:accountingSubjectCount,addFunction: true, role: 'accountingSubject', metaInfo: accountingSubjectListMetaInfo, renderItem: GlobalComponents.AccountingSubjectBase.renderItemOfList},
{name: 'accountingPeriodList', displayName:'会计期间',viewGroup:'__no_group', type:'accountingPeriod',count:accountingPeriodCount,addFunction: true, role: 'accountingPeriod', metaInfo: accountingPeriodListMetaInfo, renderItem: GlobalComponents.AccountingPeriodBase.renderItemOfList},
{name: 'accountingDocumentTypeList', displayName:'会计凭证类型',viewGroup:'__no_group', type:'accountingDocumentType',count:accountingDocumentTypeCount,addFunction: true, role: 'accountingDocumentType', metaInfo: accountingDocumentTypeListMetaInfo, renderItem: GlobalComponents.AccountingDocumentTypeBase.renderItemOfList},
    
      	],
   		subSettingItems: [
    
      	],     	
      	
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    const renderAnalytics = this.props.renderAnalytics || defaultRenderAnalytics
    const quickFunctions = this.props.quickFunctions || internalQuickFunctions
    const renderSubjectList = this.props.renderSubjectList || internalRenderSubjectList
    
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
       
        {renderExtraHeader(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}  
        {quickFunctions(cardsData)} 
        {renderAnalytics(cardsData.cardsSource)}
        {settingListOf(cardsData.cardsSource)}
        {renderSubjectList(cardsData)}       
        {largeTextOf(cardsData.cardsSource)}
        {renderExtraFooter(cardsData.cardsSource)}
  		
      </PageHeaderLayout>
    
    )
  }
}

export default connect(state => ({
  accountSet: state._accountSet,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(AccountSetDashboard))

