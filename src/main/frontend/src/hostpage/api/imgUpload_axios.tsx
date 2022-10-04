import axios from "axios"

export const imgUpload = async (file: File) => {
  if((file as File).size >= 10 * 1024 * 1024) {
    alert('최대 파일 사이즈는 10MB 입니다.')
    return
  }

  const formData = new FormData()
  formData.append('file', file )
  try {
    const res = await axios({
      method: 'post',
      url: "http://ec2-18-118-18-126.us-east-2.compute.amazonaws.com/images",
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    
    return res.data.loation
  } catch(e) {
    alert("이미지 등록 실패")
    console.log(e)
  }
  
}


export const imagesUpload = async(files: FileList) => {
  const urlList: { url: string }[] = []

  if(files.length > 5) {
    alert('등록 가능한 이미지수는 5장 입니다')
    return
  }
  Array.from(files).forEach(file => {
    const url= imgUpload(file) as unknown as string
    const urlObj = { url: url }
    urlList.push(urlObj)
  })
    return urlList
  };


