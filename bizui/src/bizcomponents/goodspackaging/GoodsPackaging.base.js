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



const menuData = {menuName: window.trans('goods_packaging'), menuFor: "goodsPackaging",
  		subItems: [
  {name: 'goodsList', displayName: window.mtrans('goods','goods_packaging.goods_list',false), type:'goods',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('goods_packaging'), menuFor: "goodsPackaging",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('goods_packaging.id'),
  packageName: window.trans('goods_packaging.package_name'),
  rfid: window.trans('goods_packaging.rfid'),
  packageTime: window.trans('goods_packaging.package_time'),
  description: window.trans('goods_packaging.description'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'goodsPackaging') , sorter: true },
  { title: fieldLabels.packageName, debugtype: 'string', dataIndex: 'packageName', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.rfid, debugtype: 'string', dataIndex: 'rfid', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.packageTime, dataIndex: 'packageTime', render: (text, record) =>renderDateCell(text,record), sorter: true },
  { title: fieldLabels.description, debugtype: 'string', dataIndex: 'description', width: '13',render: (text, record)=>renderTextCell(text,record)},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(goodsPackaging,targetComponent)=>{

  const userContext = null
  return (
    <div key={goodsPackaging.id}>
	
      <DescriptionList  key={goodsPackaging.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{goodsPackaging.id}</Description> 
        <Description term={fieldLabels.packageName} style={{wordBreak: 'break-all'}}>{goodsPackaging.packageName}</Description> 
        <Description term={fieldLabels.rfid} style={{wordBreak: 'break-all'}}>{goodsPackaging.rfid}</Description> 
        <Description term={fieldLabels.packageTime}><div>{ moment(goodsPackaging.packageTime).format('YYYY-MM-DD')}</div></Description> 
        <Description term={fieldLabels.description} style={{wordBreak: 'break-all'}}>{goodsPackaging.description}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {packageName, rfid, packageTime, description} = formValuesToPack

	const data = {packageName, rfid, packageTime, description}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {packageName, rfid, packageTime, description} = objectToUnpack

	const data = {packageName, rfid, packageTime, description}
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
const GoodsPackagingBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default GoodsPackagingBase



