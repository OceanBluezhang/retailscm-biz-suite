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



const menuData = {menuName: window.trans('retail_store_member_gift_card'), menuFor: "retailStoreMemberGiftCard",
  		subItems: [
  {name: 'retailStoreMemberGiftCardConsumeRecordList', displayName: window.mtrans('retail_store_member_gift_card_consume_record','retail_store_member_gift_card.retail_store_member_gift_card_consume_record_list',false), type:'retailStoreMemberGiftCardConsumeRecord',icon:'gift',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('retail_store_member_gift_card'), menuFor: "retailStoreMemberGiftCard",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('retail_store_member_gift_card.id'),
  name: window.trans('retail_store_member_gift_card.name'),
  owner: window.trans('retail_store_member_gift_card.owner'),
  number: window.trans('retail_store_member_gift_card.number'),
  remain: window.trans('retail_store_member_gift_card.remain'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'retailStoreMemberGiftCard') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.owner, dataIndex: 'owner', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.number, debugtype: 'string', dataIndex: 'number', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.remain, dataIndex: 'remain', className:'money', render: (text, record) => renderMoneyCell(text, record), sorter: true  },

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(retailStoreMemberGiftCard,targetComponent)=>{

  const userContext = null
  return (
    <div key={retailStoreMemberGiftCard.id}>
	
      <DescriptionList  key={retailStoreMemberGiftCard.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{retailStoreMemberGiftCard.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{retailStoreMemberGiftCard.name}</Description> 
        <Description term={fieldLabels.owner}><div>{retailStoreMemberGiftCard.owner==null?appLocaleName(userContext,"NotAssigned"):`${retailStoreMemberGiftCard.owner.displayName}(${retailStoreMemberGiftCard.owner.id})`}
        </div></Description>
        <Description term={fieldLabels.number} style={{wordBreak: 'break-all'}}>{retailStoreMemberGiftCard.number}</Description> 
        <Description term={fieldLabels.remain}><div style={{"color":"red"}}>{retailStoreMemberGiftCard.remain}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, number, remain, ownerId} = formValuesToPack
	const owner = {id: ownerId, version: 2^31}
	const data = {name, number, remain, owner}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, number, remain, owner} = objectToUnpack
	const ownerId = owner ? owner.id : null
	const data = {name, number, remain, ownerId}
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
const RetailStoreMemberGiftCardBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default RetailStoreMemberGiftCardBase



