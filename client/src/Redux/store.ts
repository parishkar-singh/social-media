// store.ts
import { configureStore } from '@reduxjs/toolkit';
import userReducer from './userSlice';
import sortFilterReducer from './sortFilterSlice';

export const store = configureStore({
    reducer: {
        user: userReducer,
        sortFilter: sortFilterReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
