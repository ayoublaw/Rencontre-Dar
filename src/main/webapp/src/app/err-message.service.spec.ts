import { TestBed } from '@angular/core/testing';

import { ErrMessageService } from './err-message.service';

describe('ErrMessageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ErrMessageService = TestBed.get(ErrMessageService);
    expect(service).toBeTruthy();
  });
});
