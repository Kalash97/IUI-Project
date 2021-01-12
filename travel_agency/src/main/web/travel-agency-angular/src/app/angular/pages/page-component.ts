export class PageComponent {

    _menuOptions: any[] = [
        { id: 0, label: 'Treść strony' }
    ];

    _selectedMenuOption = this._menuOptions[0];

    setUpMenuOptions(options: any[]) {
        this._menuOptions = options;
        this._selectedMenuOption = this._menuOptions[0];
    }

    public isMenuOptionActive(id) {
        return this._selectedMenuOption.id == id;
    }

    public clickedMenuOption(option) {
        this._selectedMenuOption = option;
    }

    public getMenuOptions() {
        return this._menuOptions;
    }
}