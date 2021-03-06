import React from 'react'
import { Icon,Divider, Avata, Card, Col} from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList

const {
	defaultRenderReferenceCell,
	defaultRenderBooleanCell,
	defaultRenderMoneyCell,
	defaultRenderDateTimeCell,
	defaultRenderImageCell,
	defaultRenderAvatarCell,
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
	defaultSearchLocalData,
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
const renderAvatarCell=defaultRenderAvatarCell
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell



const menuData = {menuName: window.trans('city_partner'), menuFor: "cityPartner",
  		subItems: [
  {name: 'potentialCustomerList', displayName: window.mtrans('potential_customer','city_partner.potential_customer_list',false), type:'potentialCustomer',icon:'om',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'potentialCustomerContactList', displayName: window.mtrans('potential_customer_contact','city_partner.potential_customer_contact_list',false), type:'potentialCustomerContact',icon:'om',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('city_partner'), menuFor: "cityPartner",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('city_partner.id'),
  name: window.trans('city_partner.name'),
  mobile: window.trans('city_partner.mobile'),
  cityServiceCenter: window.trans('city_partner.city_service_center'),
  description: window.trans('city_partner.description'),
  lastUpdateTime: window.trans('city_partner.last_update_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'cityPartner') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.cityServiceCenter, dataIndex: 'cityServiceCenter', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.description, debugtype: 'string', dataIndex: 'description', width: '20',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.lastUpdateTime, dataIndex: 'lastUpdateTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(cityPartner,targetComponent)=>{

  const userContext = null
  return (
    <div key={cityPartner.id}>
	
      <DescriptionList  key={cityPartner.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{cityPartner.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{cityPartner.name}</Description> 
        <Description term={fieldLabels.mobile} style={{wordBreak: 'break-all'}}>{cityPartner.mobile}</Description> 
        <Description term={fieldLabels.cityServiceCenter}><div>{cityPartner.cityServiceCenter==null?appLocaleName(userContext,"NotAssigned"):`${cityPartner.cityServiceCenter.displayName}(${cityPartner.cityServiceCenter.id})`}
        </div></Description>
        <Description term={fieldLabels.description} style={{wordBreak: 'break-all'}}>{cityPartner.description}</Description> 
        <Description term={fieldLabels.lastUpdateTime}><div>{ moment(cityPartner.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, mobile, description, cityServiceCenterId} = formValuesToPack
	const cityServiceCenter = {id: cityServiceCenterId, version: 2^31}
	const data = {name, mobile, description, cityServiceCenter}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, mobile, description, cityServiceCenter} = objectToUnpack
	const cityServiceCenterId = cityServiceCenter ? cityServiceCenter.id : null
	const data = {name, mobile, description, cityServiceCenterId}
	return data
}
const stepOf=(targetComponent, title, content, position, index)=>{
	return {
		title,
		content,
		position,
		packFunction: packFormValuesToObject,
		unpackFunction: unpackObjectToFormValues,
		index,
      }
}
const CityPartnerBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default CityPartnerBase



