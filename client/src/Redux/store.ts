// store.ts
import { createStore, combineReducers } from 'redux';
import userReducer from './userSlice'; // Assuming you have a userSlice reducer

// Define a function to load state from local storage
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

// Define a function to save state to local storage
const saveState = (state: any) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem('reduxState', serializedState);
    } catch {
        // ignore write errors
    }
};

// Create a rootReducer combining all reducers
const rootReducer = combineReducers({
    user: userReducer,
    // Add other reducers here if you have them
});

// Create the initial state by loading from local storage
const persistedState = loadState();

// Create the Redux store
export const store = createStore(
    rootReducer,
    persistedState
);

// Subscribe to store changes to save state to local storage
store.subscribe(() => {
    saveState(store.getState());
});
