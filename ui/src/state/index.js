import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: null,
  token: null,
  favorite: [],
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setLogin: (state, action) => {
      state.user = action.payload.user;
      state.token = action.payload.token;
    },
    setLogout: (state) => {
      state.user = null;
      state.token = null;
    },

    setFavorite: (state, action) => {
      const updatedPosts = state.posts.map((post) => {
        if (post.id === action.payload.post.id) return action.payload.post;
        return post;
      });
      state.posts = updatedPosts;
    },
  },
});

export const { setLogin, setLogout, setFavorite } = authSlice.actions;
export default authSlice.reducer;
