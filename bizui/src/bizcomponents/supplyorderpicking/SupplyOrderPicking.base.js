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



const menuData = {menuName: window.trans('supply_order_picking'), menuFor: "supplyOrderPicking",
  		subItems: [
  {name: 'supplyOrderList', displayName: window.mtrans('supply_order','supply_order_picking.supply_order_list',false), type:'supplyOrder',icon:'first-order',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('supply_order_picking'), menuFor: "supplyOrderPicking",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('supply_order_picking.id'),
  who: window.trans('supply_order_picking.who'),
  processTime: window.trans('supply_order_picking.process_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'supplyOrderPicking') , sorter: true },
  { title: fieldLabels.who, debugtype: 'string', dataIndex: 'who', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.processTime, dataIndex: 'processTime', render: (text, record) =>renderDateCell(text,record), sorter: true },

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(supplyOrderPicking,targetComponent)=>{

  const userContext = null
  return (
    <div key={supplyOrderPicking.id}>
	
      <DescriptionList  key={supplyOrderPicking.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{supplyOrderPicking.id}</Description> 
        <Description term={fieldLabels.who} style={{wordBreak: 'break-all'}}>{supplyOrderPicking.who}</Description> 
        <Description term={fieldLabels.processTime}><div>{ moment(supplyOrderPicking.processTime).format('YYYY-MM-DD')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {who, processTime} = formValuesToPack

	const data = {who, processTime}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {who, processTime} = objectToUnpack

	const data = {who, processTime}
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
const SupplyOrderPickingBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default SupplyOrderPickingBase



