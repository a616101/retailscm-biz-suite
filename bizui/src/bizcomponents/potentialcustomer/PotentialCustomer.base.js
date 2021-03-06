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



const menuData = {menuName: window.trans('potential_customer'), menuFor: "potentialCustomer",
  		subItems: [
  {name: 'potentialCustomerContactPersonList', displayName: window.mtrans('potential_customer_contact_person','potential_customer.potential_customer_contact_person_list',false), type:'potentialCustomerContactPerson',icon:'om',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'potentialCustomerContactList', displayName: window.mtrans('potential_customer_contact','potential_customer.potential_customer_contact_list',false), type:'potentialCustomerContact',icon:'om',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'eventAttendanceList', displayName: window.mtrans('event_attendance','potential_customer.event_attendance_list',false), type:'eventAttendance',icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('potential_customer'), menuFor: "potentialCustomer",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('potential_customer.id'),
  name: window.trans('potential_customer.name'),
  mobile: window.trans('potential_customer.mobile'),
  cityServiceCenter: window.trans('potential_customer.city_service_center'),
  cityPartner: window.trans('potential_customer.city_partner'),
  description: window.trans('potential_customer.description'),
  lastUpdateTime: window.trans('potential_customer.last_update_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'potentialCustomer') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.cityServiceCenter, dataIndex: 'cityServiceCenter', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cityPartner, dataIndex: 'cityPartner', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.description, debugtype: 'string', dataIndex: 'description', width: '20',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.lastUpdateTime, dataIndex: 'lastUpdateTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(potentialCustomer,targetComponent)=>{

  const userContext = null
  return (
    <div key={potentialCustomer.id}>
	
      <DescriptionList  key={potentialCustomer.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{potentialCustomer.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{potentialCustomer.name}</Description> 
        <Description term={fieldLabels.mobile} style={{wordBreak: 'break-all'}}>{potentialCustomer.mobile}</Description> 
        <Description term={fieldLabels.cityServiceCenter}><div>{potentialCustomer.cityServiceCenter==null?appLocaleName(userContext,"NotAssigned"):`${potentialCustomer.cityServiceCenter.displayName}(${potentialCustomer.cityServiceCenter.id})`}
        </div></Description>
        <Description term={fieldLabels.cityPartner}><div>{potentialCustomer.cityPartner==null?appLocaleName(userContext,"NotAssigned"):`${potentialCustomer.cityPartner.displayName}(${potentialCustomer.cityPartner.id})`}
        </div></Description>
        <Description term={fieldLabels.description} style={{wordBreak: 'break-all'}}>{potentialCustomer.description}</Description> 
        <Description term={fieldLabels.lastUpdateTime}><div>{ moment(potentialCustomer.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, mobile, description, cityServiceCenterId, cityPartnerId} = formValuesToPack
	const cityServiceCenter = {id: cityServiceCenterId, version: 2^31}
	const cityPartner = {id: cityPartnerId, version: 2^31}
	const data = {name, mobile, description, cityServiceCenter, cityPartner}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, mobile, description, cityServiceCenter, cityPartner} = objectToUnpack
	const cityServiceCenterId = cityServiceCenter ? cityServiceCenter.id : null
	const cityPartnerId = cityPartner ? cityPartner.id : null
	const data = {name, mobile, description, cityServiceCenterId, cityPartnerId}
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
const PotentialCustomerBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default PotentialCustomerBase



