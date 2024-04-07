// sortFilterSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface SortFilterState {
    sortBy: string;
    filterBy: string;
}

const initialState: SortFilterState = {
    sortBy: '',
    filterBy: '',
};

export const sortFilterSlice = createSlice({
    name: 'sortFilter',
    initialState,
    reducers: {
        setSortFilter: (state, action: PayloadAction<SortFilterState>) => {
            state.sortBy = action.payload.sortBy;
            state.filterBy = action.payload.filterBy;
        },
    },
});

export const { setSortFilter } = sortFilterSlice.actions;

export default sortFilterSlice.reducer;
