
export const registerList = [
  [ '숙박종류', 'TYPE' ],
  [ '주소', 'ADDRESS' ],
  [ '소개', 'DESCRIPTION' ],
  [ '숙박명', 'NAME' ],
  [ '공지사항', 'NOTICE' ],
  [ '정책', 'POLICY' ],
  [ '이벤트', 'EVENTS' ],
  [ '서비스', 'SERVICE' ],
  [ '이미지 등록', 'IMAGES' ]
]

export const serviceTypes = [
  '주차 가능','야외 수영장', 
  '와이파이', '피트니스',
  '24시 데스크', '목욕탕', 
  '무료 세탁', '레스토랑', 
  '커피샵', '바', 
  '연회장', '수화물 보관' 
]


//이미지등록 제한

export const sizeValidate = (file: any) => {
  if (file) {
    const fileSize = 10 * 1024 * 1024; 
    if (file.size >= fileSize) {
        return false;
      }
  }
  return true;
}

export const countValidate = (files: any) => {
  const length = files.length
  if(length > 5) return false
  return true;
}

//redycer 함수
export const roomRegisterReducer = (state: any, action: {type: string, payload: any}) => {
  const { type, payload } = action
  switch(type) {
    case 
    'type' ||  'description' ||
    'name' || 'notice' || 'policy' || 
    'locationPosition':
      return { ...state, [type]: payload}
    case 'address':
      return { ...state, addres: payload}
    case 'eventInputs':
      const eventLIst = [ ...state.eventInputs, {description: payload}]
      return { ...state, [type]: eventLIst} 
    case 'popularFacilityServiceInputs':
      const servieList = [ ...state.popularFacilityServiceInputs, {popularFacilityServiceType: payload}]
      return { ...state, [type]: servieList }
    case 'accommodationImageInputs':
      const imgList = [ ...state.accommodationImageInputs, {url: payload}]
      return { ...state, [type]: imgList }
  }   
}

export const RoomRegisterFrom = {
  type: '',
  address: '',
  description: '',
  locationPosition: {},
  name: '',
  notice: '',
  policy: '',
  eventInputs: [],
  popularFacilityServiceInputs: [],
  accommodationImageInputs: []
}