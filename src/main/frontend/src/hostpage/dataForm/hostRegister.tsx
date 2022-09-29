import ico from "../img/smile.png"

export const hostRegisterForm = [
  { name_: 'profileImage',
    label_: '이미지',
    type: 'file',
    placeholder: '이미지를 등록하세요',
    required: true,
  },
  { name_: 'name',
    label_: '호스트명',
    type: 'text',
    placeholder: '이름을 입력하세요',
    required: true,
  },
  { name_: 'businessContact',
    label_: '연락처',
    type: 'tel',
    placeholder: '연락처를 입력하세요',
    required: true,
  },
  { name_: 'companyRegistrationNumber',
    label_: '사업자 등록 번호',
    type: 'text',
    placeholder: '사업자 등록 번호를 입력하세요',
    required: true,
  },
  { name_: 'email',
    label_: '이메일',
    type: 'email',
    placeholder: 'email를 입력하세요',
    required: true
  },
]

export interface Register {
  profileImage: string
  name: string
  businessContact: string
  companyRegistrationNumber: string
  email: string
}

export type Name_ = 'profileImage' | 'businessContact' | 'companyRegistrationNumber' | 'email'
//name추가해야함

export const initialValue = {
  profileImage: ico,
  name: 'sinjjankoo',
  businessContact: '010-000-0000',
  companyRegistrationNumber: '12312321',
  email: 'ssinjjangkoo@jjankoo.com'
}