import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { GtnSharedLibsModule, GtnSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [GtnSharedLibsModule, GtnSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [GtnSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GtnSharedModule {
    static forRoot() {
        return {
            ngModule: GtnSharedModule
        };
    }
}
