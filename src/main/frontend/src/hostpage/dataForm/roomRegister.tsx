import { Room } from "../api/room_axios"

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

export const roomInitValues: Room[] = [
  {
    type: "게스트 하우스",
    address: "제주 특별시 감귤 게스트 하우스",
    description: "어서오세요, 감귤 게스트 하우스 입니다.",
    locationPosition: {},
    name: '감귤 게스트 하우스',
    notice: '편하게 즐기다 가세요~',
    policy: '놀다간 뒷처리는 깔끔하게~',
    eventInputs: [],
    popularFacilityServiceInputs: [{popularFacilityServiceType: 4}],
    accommodationImageInputs: [{url: "https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%848.png"}]
  },

  {
    type: "펜션",
    address: "충북 청주시 노원구 삼할동 떙떙번지",
    description: "호텔 뷰가 정말 좋은 장소입니다.",
    locationPosition: {},
    name: '정목호텔',
    notice: '다들 안녕하세요. 정목호텔입니다. 과도한 음주는 삼가해주시길 바랍니다.',
    policy: '제1조: 호텔을 꺠끗이 쓰도록 한다.',
    eventInputs: [],
    popularFacilityServiceInputs: [{popularFacilityServiceType: 1}],
    accommodationImageInputs: [{url: "https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%841.png"}]
  },

  {
    type: "빌라",
    address: "경남 김해시 진주빌라",
    description: "진주 빌라 입니다.",
    locationPosition: {},
    name: '진주빌라',
    notice: '어서오세요~ 진주빌라입니다.',
    policy: '1. 뒷처리는 깔끔히 한다. 2. 쓰고남은 휴지는 휴지통에 버린다.',
    eventInputs: [{description: '119'}],
    popularFacilityServiceInputs: [{popularFacilityServiceType: 5}],
    accommodationImageInputs: [{url: "https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%845.jpg"}]
  }
]