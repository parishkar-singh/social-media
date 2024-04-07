import { createStore, combineReducers } from 'redux';
import userReducer from './userSlice';
const loadState = () => {
    try {
        const serializedState = localStorage.getItem('reduxState');
        if (serializedState === null) {
            return undefined;
        }
        return JSON.parse(serializedState);
    } catch (err) {
        return undefined;
    }
};

const saveState = (state: any) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem('reduxState', serializedState);
    } catch {
    }
};

const rootReducer = combineReducers({
    user: userReducer,
});

const persistedState = loadState();

export const store = createStore(
    rootReducer,
    persistedState
);

store.subscribe(() => {
    saveState(store.getState());
});
