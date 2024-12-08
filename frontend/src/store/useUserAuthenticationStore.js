import {create} from "zustand";

const useUserAuthenticationStore = create((set) => ({
  id: null,
  role: "USER",
  setUserAuthentication: (id, role) => set({ id: id, role: role }),
}));

export default useUserAuthenticationStore;
