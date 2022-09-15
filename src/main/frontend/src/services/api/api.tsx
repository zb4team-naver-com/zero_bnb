interface props {
  key: string;
}

const storage = {
  get: ({ key }: props) => localStorage.getItem(key),
  set: ({ key, value }: props & { value: string }) =>
    localStorage.setItem(key, value),
  remove: ({ key }: props) => localStorage.removeItem(key),
};

export default storage;
